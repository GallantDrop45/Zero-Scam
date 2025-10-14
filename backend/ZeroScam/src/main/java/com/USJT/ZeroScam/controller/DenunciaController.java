package com.USJT.ZeroScam.controller;

import com.USJT.ZeroScam.model.Denuncia;
import com.USJT.ZeroScam.repository.DenunciaRepository;
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

    // Listar todas as denúncias
    @GetMapping
    public List<Denuncia> listarDenuncias() {
        return denunciaRepository.findAll();
    }

    // Criar nova denúncia
    @PostMapping
    public ResponseEntity<Denuncia> criarDenuncia(@RequestBody Denuncia denuncia) {
        try {
            Denuncia novaDenuncia = denunciaRepository.save(denuncia);
            return new ResponseEntity<>(novaDenuncia, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar denúncia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> buscarPorId(@PathVariable String id) {
        return denunciaRepository.findById(id)
                .map(denuncia -> new ResponseEntity<>(denuncia, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Buscar por canal
    @GetMapping("/canal/{canal}")
    public List<Denuncia> buscarPorCanal(@PathVariable String canal) {
        return denunciaRepository.findByCanal(canal);
    }

    // Deletar denúncia
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarDenuncia(@PathVariable String id) {
        try {
            denunciaRepository.deleteById(id);
            return new ResponseEntity<>("Denúncia deletada com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar denúncia", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}