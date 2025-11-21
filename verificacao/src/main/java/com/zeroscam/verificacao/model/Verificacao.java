package com.zeroscam.verificacao.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "verificacoes")
public class Verificacao {
    
    @Id
    private String id;
    private String link;
    private String dominio;
    private Integer scoreRisco;
    private Boolean suspeito;
    private String paisRegistro;
    private LocalDateTime dataVerificacao;
    private String usuarioId; 

    
    public Verificacao() {
        this.dataVerificacao = LocalDateTime.now();
    }

    public Verificacao(String link, String dominio, Integer scoreRisco, Boolean suspeito) {
        this.link = link;
        this.dominio = dominio;
        this.scoreRisco = scoreRisco;
        this.suspeito = suspeito;
        this.dataVerificacao = LocalDateTime.now();
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public Integer getScoreRisco() {
        return scoreRisco;
    }

    public void setScoreRisco(Integer scoreRisco) {
        this.scoreRisco = scoreRisco;
    }

    public Boolean getSuspeito() {
        return suspeito;
    }

    public void setSuspeito(Boolean suspeito) {
        this.suspeito = suspeito;
    }

    public String getPaisRegistro() {
        return paisRegistro;
    }

    public void setPaisRegistro(String paisRegistro) {
        this.paisRegistro = paisRegistro;
    }

    public LocalDateTime getDataVerificacao() {
        return dataVerificacao;
    }

    public void setDataVerificacao(LocalDateTime dataVerificacao) {
        this.dataVerificacao = dataVerificacao;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}