package listbuy.me.listbuy.entities;

/**
 * Created by mgovea on 16/11/2016.
 */
public class Produtos {

    private Long id_produto;
    private String nome;
    private String descricao;
    private Long id_categoria;
    private String ativo;
    private String data_cadastro;
    private Long id_lista;
    private Long quantidade;
    private Long id_unidade_medida;

    public Long getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(Long id_consumidor) {
        this.id_consumidor = id_consumidor;
    }

    private Long id_consumidor;

    public Produtos(Long id_produto, String nome, String descricao, Long id_categoria, String ativo, String data_cadastro, Long id_lista, Long quantidade, Long id_unidade_medida, Long id_consumidor) {
        this.id_produto = id_produto;
        this.nome = nome;
        this.descricao = descricao;
        this.id_categoria = id_categoria;
        this.ativo = ativo;
        this.data_cadastro = data_cadastro;
        this.id_lista = id_lista;
        this.quantidade = quantidade;
        this.id_unidade_medida = id_unidade_medida;
        this.id_consumidor = id_consumidor;
    }

    public Produtos() {
    }

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public Long getId_lista() {
        return id_lista;
    }

    public void setId_lista(Long id_lista) {
        this.id_lista = id_lista;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId_unidade_medida() {
        return id_unidade_medida;
    }

    public void setId_unidade_medida(Long id_unidade_medida) {
        this.id_unidade_medida = id_unidade_medida;
    }
}

