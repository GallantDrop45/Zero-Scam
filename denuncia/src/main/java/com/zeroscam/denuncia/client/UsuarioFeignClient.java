
package com.zeroscam.denuncia.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "usuario-service")
public interface UsuarioFeignClient {

    @GetMapping("/api/usuarios/{id}")
    Object buscarUsuarioPorId(@PathVariable String id);
    
    @GetMapping("/api/usuarios/email/{email}")
    Object buscarUsuarioPorEmail(@PathVariable String email);
}
