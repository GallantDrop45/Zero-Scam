package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.USJT.ZeroScam.model.Denuncia;

class DenunciaTest {

    @Test
    void deveCriarDenunciaComConstrutorVazio() {

        
        Denuncia denuncia = new Denuncia();
        
        
        assertNotNull(denuncia);
        assertNotNull(denuncia.getDataCriacao());
    }

    @Test
    void deveCriarDenunciaComTodosOsCampos() {

        
        String link = "https://golpe.com";
        String canal = "email";
        String inputExtra = "golpista@email.com";
        String perda = "sim";
        Double valorPerdido = 500.0;
        String relato = "Recebi um golpe";
        
        
        Denuncia denuncia = new Denuncia(link, canal, inputExtra, perda, valorPerdido, relato);
        
        
        assertEquals(link, denuncia.getLink());
        assertEquals(canal, denuncia.getCanal());
        assertEquals(inputExtra, denuncia.getInputExtra());
        assertEquals(perda, denuncia.getPerda());
        assertEquals(valorPerdido, denuncia.getValorPerdido());
        assertEquals(relato, denuncia.getRelato());
    }

    @Test
    void devePermitirAlterarCampos() {
        
        
        Denuncia denuncia = new Denuncia();
        
        
        denuncia.setLink("https://novo-link.com");
        denuncia.setScoreRisco(85);
        denuncia.setDominioSuspeito(true);
        
       
        assertEquals("https://novo-link.com", denuncia.getLink());
        assertEquals(85, denuncia.getScoreRisco());
        assertTrue(denuncia.getDominioSuspeito());
    }

    @Test
    void deveAceitaValorPerdidoZero(){
        
        Denuncia denuncia = new Denuncia();

        denuncia.setValorPerdido(0.0);
        assertEquals(0.0, denuncia.getValorPerdido());
        
    }
}