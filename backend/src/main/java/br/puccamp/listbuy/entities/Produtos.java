package br.puccamp.listbuy.entities;

/**
 * Created by mgovea on 16/11/2016.
 */
public class Produtos {

    private Integer ID_PRODUTO;
    private String NOME;
    private String DESCRICAO;
    private Integer ID_CATEGORIA;
    private String ATIVO;

    public Integer getID_PRODUTO() {
        return ID_PRODUTO;
    }

    public void setID_PRODUTO(Integer ID_PRODUTO) {
        this.ID_PRODUTO = ID_PRODUTO;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getDESCRICAO() {
        return DESCRICAO;
    }

    public void setDESCRICAO(String DESCRICAO) {
        this.DESCRICAO = DESCRICAO;
    }

    public Integer getID_CATEGORIA() {
        return ID_CATEGORIA;
    }

    public void setID_CATEGORIA(Integer ID_CATEGORIA) {
        this.ID_CATEGORIA = ID_CATEGORIA;
    }

    public String getATIVO() {
        return ATIVO;
    }

    public void setATIVO(String ATIVO) {
        this.ATIVO = ATIVO;
    }
}
