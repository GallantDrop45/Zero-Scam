package com.USJT.ZeroScam.service;

import com.USJT.ZeroScam.model.WhoisData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class WhoisService {
    
   
    private final String API_KEY = ""; 
    private final String API_URL = "https://whois-history.whoisxmlapi.com/api/v1";
    
    /**
     * Consulta informações WHOIS de um domínio
     */
    public WhoisData consultarDominio(String domain) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            System.out.println("⚠️ API KEY não configurada. Pulando consulta WHOIS.");
            return null;
        }
        
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "?apiKey=" + API_KEY + "&domainName=" + domain;
        
        try {
            WhoisData data = restTemplate.getForObject(url, WhoisData.class);
            System.out.println("✅ WHOIS consultado com sucesso: " + domain);
            return data;
        } catch (Exception e) {
            System.err.println("❌ Erro ao consultar WHOIS: " + e.getMessage());
            return null;
        }
    }
    
    /*
     * Calcula score de risco baseado nos dados WHOIS (0-100)
     */
    public int calcularScoreRisco(WhoisData data) {
        if (data == null) {
            return 50; // Score médio se não conseguir consultar
        }
        
        int score = 0;
        
        // 1. Verificar idade do domínio (30 pontos)
        if (data.getCreatedDate() != null) {
            long diasDesdeRegistro = calcularDiasDesdeRegistro(data.getCreatedDate());
            
            if (diasDesdeRegistro < 30) {
                score += 30; // Muito novo!
            } else if (diasDesdeRegistro < 180) {
                score += 20; // Novo
            } else if (diasDesdeRegistro < 365) {
                score += 10; // Relativamente novo
            }
        }
        
        // 2. Email com proteção de privacidade (20 pontos)
        if (data.getRegistrantEmail() != null) {
            String email = data.getRegistrantEmail().toLowerCase();
            if (email.contains("privacy") || email.contains("protected") || email.contains("whoisguard")) {
                score += 20;
            }
        }
        
        // 3. Muitas mudanças no histórico (25 pontos)
        if (data.getRecordsCount() > 10) {
            score += 25;
        } else if (data.getRecordsCount() > 5) {
            score += 15;
        }
        
        // 4. País de risco (25 pontos)
        if (data.getRegistrantCountry() != null) {
            String[] paisesRisco = {"CN", "RU", "NG", "PK", "VN"};
            for (String pais : paisesRisco) {
                if (data.getRegistrantCountry().equals(pais)) {
                    score += 25;
                    break;
                }
            }
        }
        
        return Math.min(score, 100); // Máximo 100
    }
    
    /**
     * Calcula quantos dias se passaram desde o registro do domínio
     */
    private long calcularDiasDesdeRegistro(String dataRegistro) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataRegistrada = LocalDate.parse(dataRegistro, formatter);
            LocalDate hoje = LocalDate.now();
            return ChronoUnit.DAYS.between(dataRegistrada, hoje);
        } catch (Exception e) {
            return 365; // Se der erro, assume que tem 1 ano
        }
    }
    
    /**
     * Extrai apenas o domínio de uma URL completa
     * Exemplo: https://www.google.com/search
     */
    public String extrairDominio(String url) {
        try {
            // Remove protocolo (http://, https://)
            url = url.replaceAll("^https?://", "");
            
            // Remove www.
            url = url.replaceAll("^www\\.", "");
            
            // Remove tudo depois da primeira barra
            if (url.contains("/")) {
                url = url.substring(0, url.indexOf("/"));
            }
            
            // Remove porta se tiver
            if (url.contains(":")) {
                url = url.substring(0, url.indexOf(":"));
            }
            
            return url;
        } catch (Exception e) {
            return url;
        }
    }
}