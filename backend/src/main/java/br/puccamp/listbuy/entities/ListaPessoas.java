package br.puccamp.listbuy.entities;

/**
 * Created by mgovea on 16/11/2016.
 */
public class ListaPessoas {

    private Listas listas;
    private Integer id_consumidor;

    public Listas getListas() {
        return listas;
    }

    public void setListas(Listas listas) {
        this.listas = listas;
    }

    public Integer getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(Integer id_consumidor) {
        this.id_consumidor = id_consumidor;
    }
}
