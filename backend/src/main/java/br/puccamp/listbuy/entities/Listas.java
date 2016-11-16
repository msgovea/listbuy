package br.puccamp.listbuy.entities;

import java.sql.Date;

public class Listas {

    private Integer ID_LISTA;
    private String TIPO_LISTA;
    private String TITULO;
    private Integer ID_CONSUMIDOR;
    private String ATIVA;
    private Date DATA_ICS;
    private Date DATA_ALT;

    public Date getDATA_ICS() {
        return DATA_ICS;
    }

    public void setDATA_ICS(Date DATA_ICS) {
        this.DATA_ICS = DATA_ICS;
    }

    public Date getDATA_ALT() {
        return DATA_ALT;
    }

    public void setDATA_ALT(Date DATA_ALT) {
        this.DATA_ALT = DATA_ALT;
    }

    public Integer getID_LISTA() {
        return ID_LISTA;
    }

    public void setID_LISTA(Integer ID_LISTA) {
        this.ID_LISTA = ID_LISTA;
    }

    public String getTIPO_LISTA() {
        return TIPO_LISTA;
    }

    public void setTIPO_LISTA(String TIPO_LISTA) {
        this.TIPO_LISTA = TIPO_LISTA;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
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
