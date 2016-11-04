package listbuy.me.listbuy.lista;

/**
 * Created by Talitadossantoscastr on 30/09/2016.
 */

public class ListasCriadas {

    private int idLista;
    private String nomeLista;
    private String dataCriacao;

    private ListasCriadas(int id){
        this.idLista = id;
    }
    public ListasCriadas(String nomeLista, String dataCriacao, int idLista){
        this.nomeLista = nomeLista;
        this.dataCriacao = dataCriacao;
        this.idLista = idLista;
    }
    public ListasCriadas(String nomeLista, String dataCriacao){
        this.nomeLista = nomeLista;
        this.dataCriacao = dataCriacao;
    }
    public ListasCriadas(int idLista, String nomeLista, String dataCriacao){
        this.idLista = idLista;
        this.nomeLista = nomeLista;
        this.dataCriacao = dataCriacao;
    }
    public void setId(int idLista){
        this.idLista = idLista;
    }
    public int getId(){
        return this.idLista;
    }
    public void setNomeLista(String nomeLista){
        this.nomeLista = nomeLista;
    }
    public String getNomeLista(){
        return this.nomeLista;
    }
    public void setDataCriacao(String dataCriacao){
        this.dataCriacao = dataCriacao;
    }
    public String getDataCriacao(){
        return this.dataCriacao;
    }

}
