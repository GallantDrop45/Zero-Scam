package com.zeroscam.denuncia.service;

import com.zeroscam.denuncia.model.Denuncia;
import com.zeroscam.denuncia.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    public List<Denuncia> listarTodas() {
        return denunciaRepository.findAll();
    }

    public Optional<Denuncia> buscarPorId(String id) {
        return denunciaRepository.findById(id);
    }

    public List<Denuncia> buscarPorCanal(String canal) {
        return denunciaRepository.findByCanal(canal);
    }

    public List<Denuncia> buscarPorLink(String link) {
        return denunciaRepository.findByLink(link);
    }

    public List<Denuncia> buscarSuspeitas() {
        return denunciaRepository.findByDominioSuspeitoTrue();
    }

    public Denuncia criar(Denuncia denuncia) {
        denuncia.setDataCriacao(LocalDateTime.now());
        return denunciaRepository.save(denuncia);
    }

    public Denuncia atualizar(String id, Denuncia denunciaAtualizada) {
        return denunciaRepository.findById(id)
            .map(denuncia -> {
                denuncia.setLink(denunciaAtualizada.getLink());
                denuncia.setCanal(denunciaAtualizada.getCanal());
                denuncia.setRelato(denunciaAtualizada.getRelato());
                denuncia.setScoreRisco(denunciaAtualizada.getScoreRisco());
                denuncia.setDominioSuspeito(denunciaAtualizada.getDominioSuspeito());
                return denunciaRepository.save(denuncia);
            })
            .orElseThrow(() -> new RuntimeException("Denúncia não encontrada"));
    }

    public void deletar(String id) {
        denunciaRepository.deleteById(id);
    }
}