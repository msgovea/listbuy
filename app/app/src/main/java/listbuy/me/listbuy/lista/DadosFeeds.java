package listbuy.me.listbuy.lista;

/**
 * Created by Talitadossantoscastr on 03/12/2016.
 */

public class DadosFeeds {

    private int imagem;
    private String nome;

    public DadosFeeds( String nome){
        this.nome = nome;
    }
    public DadosFeeds(int imagem, String nome){
        this.imagem = imagem;
        this.nome = nome;
    }
    public void setImagem(int imagem){
        this.imagem = imagem;
    }
    public int getImagem(){
        return this.imagem;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
}
