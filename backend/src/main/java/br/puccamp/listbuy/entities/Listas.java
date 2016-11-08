package br.puccamp.listbuy.entities;

public class Listas {

    private Integer id_lista;
    private String tipo_lista;
    private String titulo;
    private int id_consumidor;
    private String ativa;

    public Integer getId_lista() {
        return id_lista;
    }

    public void setId_lista(Integer id_lista) {
        this.id_lista = id_lista;
    }

    public String getTipo_lista() {
        return tipo_lista;
    }

    public void setTipo_lista(String tipo_lista) {
        this.tipo_lista = tipo_lista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(int id_consumidor) {
        this.id_consumidor = id_consumidor;
    }

    public String getAtiva() {
        return ativa;
    }

    public void setAtiva(String ativa) {
        this.ativa = ativa;
    }
}
