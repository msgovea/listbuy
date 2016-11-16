package br.puccamp.listbuy.entities;

import java.sql.Blob;

/**
 * Created by mateu on 16/11/2016.
 */
public class Ofertas {

    private Integer ID_OFERTA;
    private String DESCRICAO;
    private String NOME;
    private Blob IMAGEM;
    private Integer ID_CONSUMIDOR;
    private String ATIVA;

    public Integer getID_OFERTA() {
        return ID_OFERTA;
    }

    public void setID_OFERTA(Integer ID_OFERTA) {
        this.ID_OFERTA = ID_OFERTA;
    }

    public String getDESCRICAO() {
        return DESCRICAO;
    }

    public void setDESCRICAO(String DESCRICAO) {
        this.DESCRICAO = DESCRICAO;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public Blob getIMAGEM() {
        return IMAGEM;
    }

    public void setIMAGEM(Blob IMAGEM) {
        this.IMAGEM = IMAGEM;
    }

    public Integer getID_CONSUMIDOR() {
        return ID_CONSUMIDOR;
    }

    public void setID_CONSUMIDOR(Integer ID_CONSUMIDOR) {
        this.ID_CONSUMIDOR = ID_CONSUMIDOR;
    }

    public String getATIVA() {
        return ATIVA;
    }

    public void setATIVA(String ATIVA) {
        this.ATIVA = ATIVA;
    }
}
