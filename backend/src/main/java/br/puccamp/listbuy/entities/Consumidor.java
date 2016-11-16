package br.puccamp.listbuy.entities;

public class Consumidor {

    private Integer ID_CONSUMIDOR;
    private String NOME;
    private String EMAIL;
    private String SENHA;
    private String ID_TIPO_ACESSO;
    private String KEY_ACESSO;

    public Integer getID_CONSUMIDOR() {
        return ID_CONSUMIDOR;
    }

    public void setID_CONSUMIDOR(Integer ID_CONSUMIDOR) {
        this.ID_CONSUMIDOR = ID_CONSUMIDOR;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getSENHA() {
        return SENHA;
    }

    public void setSENHA(String SENHA) {
        this.SENHA = SENHA;
    }

    public String getID_TIPO_ACESSO() {
        return ID_TIPO_ACESSO;
    }

    public void setID_TIPO_ACESSO(String ID_TIPO_ACESSO) {
        this.ID_TIPO_ACESSO = ID_TIPO_ACESSO;
    }

    public String getKEY_ACESSO() {
        return KEY_ACESSO;
    }

    public void setKEY_ACESSO(String KEY_ACESSO) {
        this.KEY_ACESSO = KEY_ACESSO;
    }
}
