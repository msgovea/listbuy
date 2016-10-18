package listbuy.me.listbuy.lista;

/**
 * Created by Talitadossantoscastr on 30/09/2016.
 */

public class Produtos {

    private ListasCriadas idLista;
    private int id_listas_criadas;
    private int id_produtos; // gambiarra do id da lista rever com o Felipe
    private String descricao;
    private String unMed;
    private int qtd;
    private String qd2;
    private String valor;

    public Produtos(int id_produtos){
        this.id_produtos = id_produtos;
    }
    public Produtos(ListasCriadas idLista){
        this.idLista = idLista;
    }
    public Produtos(String descricao, int qtd, String unMed){
        // para o select que exibe os produtos no lista
        this.descricao = descricao;
        this.qtd = qtd;
        this.unMed =  unMed;
    }

    public Produtos(ListasCriadas idLista,String descricao, int qtd, String unMed){
        // para o insert que insere todos os produtos na lista
        this.idLista = idLista;
        this.descricao = descricao;
        this.qtd = qtd;
        this.unMed =  unMed;
    }
    public void setIdLista(ListasCriadas idLista){
        this.idLista = idLista;
    }
    public ListasCriadas getIdLista(){
        return this.idLista;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public void setUnMed(String unMed){
        this.unMed = unMed;
    }
    public String getUnMed(){
        return this.unMed;
    }
    public void setQtd(int qtd){
        this.qtd = qtd;
    }
    public int getQtd(){
        return this.qtd;
    }
    public void setValor(String valor){
        this.valor = valor;
    }
    public String getValor(){
        return this.valor;
    }
    public void setIdprodutos(int Id_produtos){
        this.id_produtos = id_produtos;
    }
    public int getIdprodutos(){
        return this.id_produtos;
    }
    public void setQtd2(String qtd2){
        this.qd2 = qtd2;
    }
    public String getQtd2(){
        return this.qd2;
    }

}
