package br.puccamp.listbuy.entities;

/**
 * Created by mgovea on 16/11/2016.
 */

public class UnidadeMedida {
    private Integer ID_UNIDADE_MEDIDA;
    private String DRESCICAO_UNIDADE;
    private String SIGLA;
    private String ATIVA;

    public Integer getID_UNIDADE_MEDIDA() {
        return ID_UNIDADE_MEDIDA;
    }

    public void setID_UNIDADE_MEDIDA(Integer ID_UNIDADE_MEDIDA) {
        this.ID_UNIDADE_MEDIDA = ID_UNIDADE_MEDIDA;
    }

    public String getDRESCICAO_UNIDADE() {
        return DRESCICAO_UNIDADE;
    }

    public void setDRESCICAO_UNIDADE(String DRESCICAO_UNIDADE) {
        this.DRESCICAO_UNIDADE = DRESCICAO_UNIDADE;
    }

    public String getSIGLA() {
        return SIGLA;
    }

    public void setSIGLA(String SIGLA) {
        this.SIGLA = SIGLA;
    }

    public String getATIVA() {
        return ATIVA;
    }

    public void setATIVA(String ATIVA) {
        this.ATIVA = ATIVA;
    }
}
