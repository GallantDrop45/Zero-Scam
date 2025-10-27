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

    // API Who-is-Data
    private Integer scoreRisco; // 0-100
    private String dominio; // google.com
    private String paisRegistrante; //BR, US, etc
    private String dataCriacaoDominio; // yyyy-MM-dd
    private Boolean dominioSuspeito; // Sim/Não


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

    public Integer getScoreRisco() {
        return scoreRisco;
    }

    public void setScoreRisco(Integer scoreRisco) {
        this.scoreRisco = scoreRisco;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getPaisRegistro() {
        return paisRegistrante;
    }

    public void setPaisRegistro(String paisRegistro) {
        this.paisRegistrante = paisRegistro;
    }

    public String getDataRegistroDominio() {
        return dataCriacaoDominio;
    }

    public void setDataRegistroDominio(String dataRegistroDominio) {
        this.dataCriacaoDominio = dataRegistroDominio;
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

    public Boolean getDominioSuspeito() {
    return dominioSuspeito;
    }

    public void setDominioSuspeito(Boolean dominioSuspeito) {
    this.dominioSuspeito = dominioSuspeito;
    }
}