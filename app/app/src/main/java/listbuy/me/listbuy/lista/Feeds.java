package listbuy.me.listbuy.lista;

/**
 * Created by Talitadossantoscastr on 30/11/2016.
 */

public class Feeds {
    private int imagem;
    private String nome;

    public Feeds(String nome){
        this.nome = nome;
    }
    public Feeds(int imagem, String nome){
        this.imagem = imagem;
        this.nome = nome;
    }
    public void setImagem(int imagem){
        this.imagem = imagem;
    }
    public int getImage(){
        return this.imagem;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
}
