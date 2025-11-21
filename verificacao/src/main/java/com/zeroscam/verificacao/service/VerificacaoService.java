package com.zeroscam.verificacao.service;

import com.zeroscam.verificacao.model.Verificacao;
import com.zeroscam.verificacao.repository.VerificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VerificacaoService {

    @Autowired
    private VerificacaoRepository verificacaoRepository;

    public List<Verificacao> listarTodas() {
        return verificacaoRepository.findAll();
    }

    public Optional<Verificacao> buscarPorId(String id) {
        return verificacaoRepository.findById(id);
    }

    public Optional<Verificacao> buscarPorLink(String link) {
        return verificacaoRepository.findByLink(link);
    }

    public List<Verificacao> buscarPorDominio(String dominio) {
        return verificacaoRepository.findByDominio(dominio);
    }

    public List<Verificacao> buscarSuspeitos() {
        return verificacaoRepository.findBySuspeitoTrue();
    }

    public List<Verificacao> buscarPorScoreMinimo(Integer scoreMinimo) {
        return verificacaoRepository.findByScoreRiscoGreaterThanEqual(scoreMinimo);
    }

    public List<Verificacao> buscarPorUsuario(String usuarioId) {
        return verificacaoRepository.findByUsuarioId(usuarioId);
    }

    public Verificacao verificarLink(String link) {
        Optional<Verificacao> existente = buscarPorLink(link);
        if (existente.isPresent()) {
            return existente.get();
        }

        Verificacao verificacao = new Verificacao();
        verificacao.setLink(link);
        verificacao.setDominio(extrairDominio(link));
        verificacao.setDataVerificacao(LocalDateTime.now());
        verificacao.setScoreRisco(calcularRisco(link));
        verificacao.setSuspeito(verificacao.getScoreRisco() >= 50);

        return verificacaoRepository.save(verificacao);
    }

    public Verificacao criar(Verificacao verificacao) {
        verificacao.setDataVerificacao(LocalDateTime.now());
        return verificacaoRepository.save(verificacao);
    }

    public Verificacao atualizar(String id, Verificacao verificacaoAtualizada) {
        return verificacaoRepository.findById(id)
            .map(verificacao -> {
                verificacao.setLink(verificacaoAtualizada.getLink());
                verificacao.setDominio(verificacaoAtualizada.getDominio());
                verificacao.setScoreRisco(verificacaoAtualizada.getScoreRisco());
                verificacao.setSuspeito(verificacaoAtualizada.getSuspeito());
                verificacao.setPaisRegistro(verificacaoAtualizada.getPaisRegistro());
                return verificacaoRepository.save(verificacao);
            })
            .orElseThrow(() -> new RuntimeException("Verificação não encontrada"));
    }

    public void deletar(String id) {
        verificacaoRepository.deleteById(id);
    }

    private String extrairDominio(String link) {
        try {
            String dominio = link.replace("https://", "").replace("http://", "");
            int barraIndex = dominio.indexOf("/");
            if (barraIndex > 0) {
                dominio = dominio.substring(0, barraIndex);
            }
            return dominio;
        } catch (Exception e) {
            return link;
        }
    }

    private Integer calcularRisco(String link) {
        int risco = 0;

        if (link.startsWith("http://")) {
            risco += 20;
        }

        if (link.matches(".*\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}.*")) {
            risco += 30;
        }

        if (link.length() > 100) {
            risco += 15;
        }

        String linkLower = link.toLowerCase();
        if (linkLower.contains(".tk") || linkLower.contains(".ml") || 
            linkLower.contains(".ga") || linkLower.contains(".cf")) {
            risco += 25;
        }

        return Math.min(100, risco);
    }
}