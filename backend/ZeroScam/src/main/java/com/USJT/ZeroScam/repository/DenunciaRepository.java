package com.USJT.ZeroScam.repository;

import com.USJT.ZeroScam.model.Denuncia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DenunciaRepository extends MongoRepository<Denuncia, String> {
    List<Denuncia> findByCanal(String canal);
    List<Denuncia> findByPerda(String perda);
}   