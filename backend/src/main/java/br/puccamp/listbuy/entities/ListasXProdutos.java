package br.puccamp.listbuy.entities;

/**
 * Created by mgovea on 16/11/2016.
 */
public class ListasXProdutos {

    private Integer ID_LISTA;
    private Integer ID_PRODUTO;
    private Integer QUANTIDADE;
    private Integer ID_UNIDADE_MEDIDA;

    public Integer getID_LISTA() {
        return ID_LISTA;
    }

    public void setID_LISTA(Integer ID_LISTA) {
        this.ID_LISTA = ID_LISTA;
    }

    public Integer getID_PRODUTO() {
        return ID_PRODUTO;
    }

    public void setID_PRODUTO(Integer ID_PRODUTO) {
        this.ID_PRODUTO = ID_PRODUTO;
    }

    public Integer getQUANTIDADE() {
        return QUANTIDADE;
    }

    public void setQUANTIDADE(Integer QUANTIDADE) {
        this.QUANTIDADE = QUANTIDADE;
    }

    public Integer getID_UNIDADE_MEDIDA() {
        return ID_UNIDADE_MEDIDA;
    }

    public void setID_UNIDADE_MEDIDA(Integer ID_UNIDADE_MEDIDA) {
        this.ID_UNIDADE_MEDIDA = ID_UNIDADE_MEDIDA;
    }
}
