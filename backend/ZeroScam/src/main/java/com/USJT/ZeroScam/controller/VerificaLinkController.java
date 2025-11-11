package com.USJT.ZeroScam.controller;

import com.USJT.ZeroScam.model.Denuncia;
import com.USJT.ZeroScam.model.WhoisData;
import com.USJT.ZeroScam.repository.DenunciaRepository;
import com.USJT.ZeroScam.service.WhoisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class VerificaLinkController {

    @Autowired
    private WhoisService whoisService;

    @Autowired
    private DenunciaRepository denunciaRepository;

 
    @PostMapping("/verificar-link")
    public ResponseEntity<?> verificarLink(@RequestBody Map<String, String> request) {
        try {
            String link = request.get("link");
            
            if (link == null || link.isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(criarResposta(false, "Link não fornecido", null));
            }

            System.out.println("🔍 Verificando link: " + link);

            
            String dominio = whoisService.extrairDominio(link);
            
            List<Denuncia> denuncias = denunciaRepository.findAll();
            long totalDenuncias = denuncias.stream()
                .filter(d -> d.getDominio() != null && d.getDominio().equalsIgnoreCase(dominio))
                .count();
            
            
            double valorTotal = denuncias.stream()
                .filter(d -> d.getDominio() != null && d.getDominio().equalsIgnoreCase(dominio))
                .mapToDouble(d -> d.getValorPerdido() != null ? d.getValorPerdido() : 0.0)
                .sum();

            
            WhoisData whoisData = whoisService.consultarDominio(dominio);
            
           
            int scoreRisco = whoisService.calcularScoreRisco(whoisData);
            
           
            boolean dominioRegistrado = whoisData != null && 
                                       whoisData.getCreatedDate() != null;

           
            String dicaSeguranca = gerarDicaSeguranca(scoreRisco, totalDenuncias);

            
            Map<String, Object> analise = new HashMap<>();
            analise.put("link", link);
            analise.put("dominio", dominio);
            analise.put("totalDenuncias", totalDenuncias);
            analise.put("valorTotalPerdido", valorTotal);
            analise.put("scoreRisco", scoreRisco);
            analise.put("dominioRegistrado", dominioRegistrado);
            analise.put("dicaSeguranca", dicaSeguranca);
            analise.put("suspeito", scoreRisco >= 60 || totalDenuncias >= 5);
            
            
            if (whoisData != null) {
                analise.put("paisRegistro", whoisData.getRegistrantCountry());
                analise.put("dataRegistro", whoisData.getCreatedDate());
            }

            System.out.println("Análise concluida - Score: " + scoreRisco);

            return ResponseEntity.ok(criarResposta(true, "Analise concluida", analise));

        } catch (Exception e) {
            System.err.println("Erro ao verificar link: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                .body(criarResposta(false, "Não é possivel analisar o link no momento", null));
        }
    }

   
    private String gerarDicaSeguranca(int score, long denuncias) {
        if (score >= 80 || denuncias >= 10) {
            return "Alerta vermelho! Este link apresenta alto risco de golpe. Evite acessá-lo e, se possível, denuncie.";
        } else if (score >= 60 || denuncias >= 5) {
            return "Cuidado! Este link pode ser perigoso. Não forneça dados pessoais ou financeiros.";
        } else if (score >= 40 || denuncias >= 2) {
            return "Atenção! Há indícios de risco. Verifique cuidadosamente antes de prosseguir.";
        } else if (score >= 20) {
            return "Risco moderado detectado. Mantenha precaução ao acessar este link.";
        } else {
            return "Nenhum risco significativo detectado. Mesmo assim, mantenha boas práticas de segurança.";
        }
    }

    private Map<String, Object> criarResposta(boolean sucesso, String mensagem, Object dados) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("sucesso", sucesso);
        resposta.put("mensagem", mensagem);
        if (dados != null) {
            resposta.put("dados", dados);
        }
        return resposta;
    }
}