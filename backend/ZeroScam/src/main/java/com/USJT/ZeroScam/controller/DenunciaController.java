package com.USJT.ZeroScam.controller;

import com.USJT.ZeroScam.model.Denuncia;
import com.USJT.ZeroScam.model.WhoisData;
import com.USJT.ZeroScam.repository.DenunciaRepository;
import com.USJT.ZeroScam.service.WhoisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
@CrossOrigin(origins = "http://localhost:3000")
public class DenunciaController {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private WhoisService whoisService;

    
    @GetMapping
    public List<Denuncia> listarDenuncias() {
        return denunciaRepository.findAll();
    }

   
     @PostMapping
    public ResponseEntity<Denuncia> criarDenuncia(@RequestBody Denuncia denuncia) {
        try {
            System.out.println("📥 Recebendo denúncia: " + denuncia.getLink());
            
            
            String dominio = whoisService.extrairDominio(denuncia.getLink());
            denuncia.setDominio(dominio);
            System.out.println("Domínio extraído: " + dominio);

            if (whoisService.consultarDominio(dominio) != null) {
                WhoisData whoisData = whoisService.consultarDominio(dominio);

                if (whoisData != null) {

                    int scoreRisco = whoisService.calcularScoreRisco(whoisData);
                    denuncia.setScoreRisco(scoreRisco);
                    
                    
                    denuncia.setPaisRegistro(whoisData.getRegistrantCountry());
                    denuncia.setDataRegistroDominio(whoisData.getCreatedDate());
                    
                    //  Marcar como suspeito se score >= 60
                    denuncia.setDominioSuspeito(scoreRisco >= 60);
                    
                    System.out.println("Domínio verificado! Score: " + scoreRisco);
                } else {
                    System.out.println("WHOIS não consultado (API KEY não configurada ou erro)");
                }
            }

            Denuncia novaDenuncia = denunciaRepository.save(denuncia);
            System.out.println("Denúncia salva com ID: " + novaDenuncia.getId());
            
            return new ResponseEntity<>(novaDenuncia, HttpStatus.CREATED);

        } catch (Exception e) {
            System.err.println("Erro ao criar denúncia: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> buscarPorId(@PathVariable String id) {
        return denunciaRepository.findById(id)
                .map(denuncia -> new ResponseEntity<>(denuncia, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/canal/{canal}")
    public List<Denuncia> buscarPorCanal(@PathVariable String canal) {
        return denunciaRepository.findByCanal(canal);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarDenuncia(@PathVariable String id) {
        try {
            denunciaRepository.deleteById(id);
            return new ResponseEntity<>("Denúncia deletada com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar denúncia", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}   