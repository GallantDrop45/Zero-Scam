package com.USJT.ZeroScam.service;

import com.USJT.ZeroScam.model.Usuario;
import com.USJT.ZeroScam.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org. springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) throws Exception {
        if(usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new Exception("Email já cadastrado");
        }

        String senhacriptografada = Integer.toString(usuario.getSenha().hashCode()); 
        usuario.setSenha(senhacriptografada);

        Usuario novoUsuario = usuarioRepository.save(usuario);

        System.out.println("Usuário cadastrado com sucesso: " + novoUsuario.getEmail());
        return novoUsuario;
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public boolean verificarCrencialiais(String email, String senha) {
        Usuario usuario = buscarPorEmail(email);
        if (usuario == null) {
            return false;
        }
        String senhacriptografada = Integer.toString(senha.hashCode());
        return usuario.getSenha().equals(senhacriptografada);
    }

    private String criptografarSenha(String senha) {
        return Integer.toString(senha.hashCode());
    }

}
