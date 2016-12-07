package listbuy.me.listbuy.lista;

/**
 * Created by Talitadossantoscastr on 16/10/2016.
 */

public class CategoriasProdutos {
    //private int id_categgoria;
    private int id_imagem;
    private String categoria;

    public CategoriasProdutos(int id_imagem, String categoria){
        this.id_imagem = id_imagem;
        this.categoria = categoria;
    }
    public void setId_imagem(int id_imagem){
        this.id_imagem = id_imagem;
    }
    public int getId_imagem(){
        return  this.id_imagem;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    public String getCategoria(){
        return this.categoria;
    }
}
