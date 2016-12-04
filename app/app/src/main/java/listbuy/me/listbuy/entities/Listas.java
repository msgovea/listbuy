package listbuy.me.listbuy.entities;

import java.sql.Date;

public class Listas {

    private Long id_lista;
    private String tipo_lista;
    private String titulo;
    private Long id_consumidor;
    private String ativa;
    private String data_ics;
    private String data_alt;

    public Long getId_lista() {
        return id_lista;
    }

    public void setId_lista(Long id_lista) {
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

    public Long getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(Long id_consumidor) {
        this.id_consumidor = id_consumidor;
    }

    public String getAtiva() {
        return ativa;
    }

    public void setAtiva(String ativa) {
        this.ativa = ativa;
    }

    public String getData_ics() {
        return data_ics;
    }

    public void setData_ics(String data_ics) {
        this.data_ics = data_ics;
    }

    public String getData_alt() {
        return data_alt;
    }

    public void setData_alt(String data_alt) {
        this.data_alt = data_alt;
    }
}
