package com.USJT.ZeroScam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "denuncias")
public class Denuncia {
    
    @Id
    private String id;
    private String link;
    private String canal;
    private String inputExtra;
    private String perda;
    private Double valorPerdido;
    private String relato;
    private LocalDateTime dataCriacao;

    
    public Denuncia() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Denuncia(String link, String canal, String inputExtra, String perda, Double valorPerdido, String relato) {
        this.link = link;
        this.canal = canal;
        this.inputExtra = inputExtra;
        this.perda = perda;
        this.valorPerdido = valorPerdido;
        this.relato = relato;
        this.dataCriacao = LocalDateTime.now();
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

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getInputExtra() {
        return inputExtra;
    }

    public void setInputExtra(String inputExtra) {
        this.inputExtra = inputExtra;
    }

    public String getPerda() {
        return perda;
    }

    public void setPerda(String perda) {
        this.perda = perda;
    }

    public Double getValorPerdido() {
        return valorPerdido;
    }

    public void setValorPerdido(Double valorPerdido) {
        this.valorPerdido = valorPerdido;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}