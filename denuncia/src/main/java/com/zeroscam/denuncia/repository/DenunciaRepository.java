package com.zeroscam.denuncia.repository;

import com.zeroscam.denuncia.model.Denuncia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenunciaRepository extends MongoRepository<Denuncia, String> {
    
    List<Denuncia> findByCanal(String canal);
    
    
    List<Denuncia> findByLink(String link);
    
    
    List<Denuncia> findByDominio(String dominio);
    
    
    List<Denuncia> findByDominioSuspeitoTrue();
    
    
    List<Denuncia> findByScoreRiscoGreaterThanEqual(Integer scoreRisco);
    
    
    List<Denuncia> findByPaisRegistrante(String paisRegistrante);
}