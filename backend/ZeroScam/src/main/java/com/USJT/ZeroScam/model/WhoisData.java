package com.USJT.ZeroScam.model;

import java.time.LocalDateTime;

public class WhoisData {
    private String dominio;
    private String NomeRegistrante;
    private String emailRegistrante;
    private String PaisRegistrante;
    private String dataCriacao;
    private String dataAtualizacao;
    private String dataExpiracao;
    private int recordsCount;
    private String registrarName;
    

    public WhoisData() {}

    
    public String getDomainName() {
        return dominio;
    }

    public void setDomainName(String domainName) {
        this.dominio = domainName;
    }

    public String getRegistrantName() {
        return NomeRegistrante;
    }

    public void setRegistrantName(String registrantName) {
        this.NomeRegistrante = registrantName;
    }

    public String getRegistrantEmail() {
        return emailRegistrante;
    }

    public void setRegistrantEmail(String registrantEmail) {
        this.emailRegistrante = registrantEmail;
    }

    public String getRegistrantCountry() {
        return PaisRegistrante;
    }

    public void setRegistrantCountry(String registrantCountry) {
        this.PaisRegistrante = registrantCountry;
    }

    public String getCreatedDate() {
        return dataCriacao;
    }

    public void setCreatedDate(String createdDate) {
        this.dataCriacao = createdDate;
    }

    public String getUpdatedDate() {
        return dataAtualizacao;
    }

    public void setUpdatedDate(String updatedDate) {
        this.dataAtualizacao = updatedDate;
    }

    public String getExpiresDate() {
        return dataExpiracao;
    }

    public void setExpiresDate(String expiresDate) {
        this.dataExpiracao = expiresDate;
    }

    public int getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(int recordsCount) {
        this.recordsCount = recordsCount;
    }

    public String getRegistrarName() {
        return registrarName;
    }

    public void setRegistrarName(String registrarName) {
        this.registrarName = registrarName;
    }
}