package br.puccamp.listbuy.service;

import br.puccamp.listbuy.dao.ListDAO;
import br.puccamp.listbuy.dao.ProductDAO;
import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.entities.Produtos;

import java.util.List;

public class ProductService {

    ProductDAO productDAO = new ProductDAO();

    public List<Produtos> listarProdutosPorLista(int idLista) {

        Listas lista = productDAO.listarInformacoesListas(idLista);
        List<Produtos> produtos = listDAO.listarProdutosPorLista(idLista);
        if (produtos == null) {
            throw new RuntimeException("Email ou senha inválido!");
        }
        return produtos;
    }

}
