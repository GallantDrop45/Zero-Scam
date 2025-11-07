package com.USJT.ZeroScam.controller;

import com.USJT.ZeroScam.model.Usuario;
import com.USJT.ZeroScam.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MyController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from the Backend!";
    }

   
    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    
    @PostMapping("/usuarios")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

   
    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable String id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    
    @DeleteMapping("/usuarios/{id}")
    public String deletarUsuario(@PathVariable String id) {
        usuarioRepository.deleteById(id);
        return "Usuário deletado com sucesso!";
    }


    
}