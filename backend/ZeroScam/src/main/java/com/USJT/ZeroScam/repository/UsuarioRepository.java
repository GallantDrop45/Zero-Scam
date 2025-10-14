package com.USJT.ZeroScam.repository;

import com.USJT.ZeroScam.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    // Métodos personalizados (opcional)
    Usuario findByEmail(String email);
}