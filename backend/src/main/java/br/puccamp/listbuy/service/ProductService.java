package br.puccamp.listbuy.service;

import br.puccamp.listbuy.dao.ProductDAO;
import br.puccamp.listbuy.entities.Produtos;

import java.util.List;

public class ProductService {

    ProductDAO productDAO = new ProductDAO();

    public List<Produtos> listarProdutosPorLista(Long idLista) {

        List<Produtos> produtos = productDAO.listarInformacoesLista(idLista);
        return produtos;
    }

    public Produtos atualizarProdutos(Produtos dados) {
        Produtos produto = productDAO.atualizarProdutos(dados);
        if (produto == null) {
            throw new RuntimeException("Erro ao atualizar informações da lista!");
        }
        return produto;
    }

    public Produtos registrarProduto(Produtos produto) {
        Produtos produtos = productDAO.registrarProdutos(produto);
        if (produto == null) {
            throw new RuntimeException("Erro ao cadastrar produto");
        }
        return produto;
    }
}
