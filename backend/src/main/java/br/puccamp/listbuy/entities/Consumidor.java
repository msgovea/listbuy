package br.puccamp.listbuy.entities;

public class Consumidor {

    private Integer id_consumidor;
    private String nome;
    private String email;
    private String senha;
    private String id_tipo_acesso;
    private String key_acesso;

    public Integer getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(Integer id_consumidor) {
        this.id_consumidor = id_consumidor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getId_tipo_acesso() {
        return id_tipo_acesso;
    }

    public void setId_tipo_acesso(String id_tipo_acesso) {
        this.id_tipo_acesso = id_tipo_acesso;
    }

    public String getKey_acesso() {
        return key_acesso;
    }

    public void setKey_acesso(String key_acesso) {
        this.key_acesso = key_acesso;
    }
}
