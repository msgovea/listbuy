package listbuy.me.listbuy.entities;

/**
 * Created by mgovea on 16/11/2016.
 */

public class UnidadeMedida {
    private Integer id_unidade_medida;
    private String drescicao_unidade;
    private String sigla;
    private String ativa;

    public Integer getId_unidade_medida() {
        return id_unidade_medida;
    }

    public void setId_unidade_medida(Integer id_unidade_medida) {
        this.id_unidade_medida = id_unidade_medida;
    }

    public String getDrescicao_unidade() {
        return drescicao_unidade;
    }

    public void setDrescicao_unidade(String drescicao_unidade) {
        this.drescicao_unidade = drescicao_unidade;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getAtiva() {
        return ativa;
    }

    public void setAtiva(String ativa) {
        this.ativa = ativa;
    }
}
