package br.puccamp.listbuy.entities;

/**
 * Created by mgovea on 16/11/2016.
 */
public class ListasXProdutos {

    private Long id_lista;
    private Long id_produto;
    private Long quantidade;
    private Long id_unidade_medida;

    public Long getId_lista() {
        return id_lista;
    }

    public void setId_lista(Long id_lista) {
        this.id_lista = id_lista;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId_unidade_medida() {
        return id_unidade_medida;
    }

    public void setId_unidade_medida(Long id_unidade_medida) {
        this.id_unidade_medida = id_unidade_medida;
    }
}
