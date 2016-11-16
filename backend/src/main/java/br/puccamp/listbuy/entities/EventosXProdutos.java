package br.puccamp.listbuy.entities;

/**
 * Created by mgovea on 16/11/2016.
 */
public class EventosXProdutos {

    private Integer id_lista;
    private Integer id_produto;
    private Integer id_consumidor_assign;
    private Integer quantidade;
    private Integer id_unidade_medida;

    public Integer getId_lista() {
        return id_lista;
    }

    public void setId_lista(Integer id_lista) {
        this.id_lista = id_lista;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public Integer getId_consumidor_assign() {
        return id_consumidor_assign;
    }

    public void setId_consumidor_assign(Integer id_consumidor_assign) {
        this.id_consumidor_assign = id_consumidor_assign;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getId_unidade_medida() {
        return id_unidade_medida;
    }

    public void setId_unidade_medida(Integer id_unidade_medida) {
        this.id_unidade_medida = id_unidade_medida;
    }
}
