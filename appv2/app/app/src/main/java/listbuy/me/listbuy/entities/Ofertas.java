package listbuy.me.listbuy.entities;

import java.sql.Blob;

/**
 * Created by mateu on 16/11/2016.
 */
public class Ofertas {

    private Integer id_oferta;
    private String descricao;
    private String nome;
    private Blob imagem;
    private Integer id_consumidor;
    private String ativa;

    public Integer getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(Integer id_oferta) {
        this.id_oferta = id_oferta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Blob getImagem() {
        return imagem;
    }

    public void setImagem(Blob imagem) {
        this.imagem = imagem;
    }

    public Integer getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(Integer id_consumidor) {
        this.id_consumidor = id_consumidor;
    }

    public String getAtiva() {
        return ativa;
    }

    public void setAtiva(String ativa) {
        this.ativa = ativa;
    }
}
