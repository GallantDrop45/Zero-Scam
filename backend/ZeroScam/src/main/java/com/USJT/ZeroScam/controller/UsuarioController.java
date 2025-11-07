package com.USJT.ZeroScam.controller;

import com.USJT.ZeroScam.model.Usuario;
import com.USJT.ZeroScam.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.registrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        String email = credenciais.get("email");
        String senha = credenciais.get("senha");

        boolean autenticado = usuarioService.verificarCrencialiais(email, senha);
        if (autenticado) {
            Usuario usuario = usuarioService.buscarPorEmail(email);
            return ResponseEntity.ok(usuario);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("erro", "Credenciais inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/verificarCredenciais")
    public ResponseEntity<?> verificarCredenciais(@RequestParam String email, @RequestParam String senha) {
        boolean autenticado = usuarioService.verificarCrencialiais(email, senha);
        if (autenticado) {
            return ResponseEntity.ok().build();
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("erro", "Credenciais invalidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}
