package br.puccamp.listbuy.service;

import br.puccamp.listbuy.dao.ListDAO;
import br.puccamp.listbuy.dao.ProductDAO;
import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.entities.Produtos;

import java.util.List;

public class ProductService {

    ProductDAO productDAO = new ProductDAO();

    public List<Produtos> listarProdutosPorLista(Long idLista) {

        List<Produtos> produtos = productDAO.listarInformacoesLista(idLista);
        return produtos;
    }

}
