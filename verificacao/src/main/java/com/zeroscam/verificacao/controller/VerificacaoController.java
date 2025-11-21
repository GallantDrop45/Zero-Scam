package com.zeroscam.verificacao.controller;

import com.zeroscam.verificacao.model.Verificacao;
import com.zeroscam.verificacao.service.VerificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verificacoes")
@CrossOrigin(origins = "http://localhost:3000")
public class VerificacaoController {

    @Autowired
    private VerificacaoService verificacaoService;

    @GetMapping
    public ResponseEntity<List<Verificacao>> listarTodas() {
        return ResponseEntity.ok(verificacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Verificacao> buscarPorId(@PathVariable String id) {
        return verificacaoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/link")
    public ResponseEntity<Verificacao> buscarPorLink(@RequestParam String link) {
        return verificacaoService.buscarPorLink(link)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dominio/{dominio}")
    public ResponseEntity<List<Verificacao>> buscarPorDominio(@PathVariable String dominio) {
        return ResponseEntity.ok(verificacaoService.buscarPorDominio(dominio));
    }

    @GetMapping("/suspeitos")
    public ResponseEntity<List<Verificacao>> buscarSuspeitos() {
        return ResponseEntity.ok(verificacaoService.buscarSuspeitos());
    }

    @GetMapping("/alto-risco")
    public ResponseEntity<List<Verificacao>> buscarAltoRisco(
            @RequestParam(defaultValue = "70") Integer scoreMinimo) {
        return ResponseEntity.ok(verificacaoService.buscarPorScoreMinimo(scoreMinimo));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Verificacao>> buscarPorUsuario(@PathVariable String usuarioId) {
        return ResponseEntity.ok(verificacaoService.buscarPorUsuario(usuarioId));
    }

    @PostMapping("/verificar")
    public ResponseEntity<Verificacao> verificarLink(@RequestParam String link) {
        Verificacao verificacao = verificacaoService.verificarLink(link);
        return ResponseEntity.status(HttpStatus.CREATED).body(verificacao);
    }

    @PostMapping
    public ResponseEntity<Verificacao> criar(@RequestBody Verificacao verificacao) {
        Verificacao novaVerificacao = verificacaoService.criar(verificacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVerificacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Verificacao> atualizar(
            @PathVariable String id,
            @RequestBody Verificacao verificacao) {
        try {
            Verificacao verificacaoAtualizada = verificacaoService.atualizar(id, verificacao);
            return ResponseEntity.ok(verificacaoAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        verificacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}