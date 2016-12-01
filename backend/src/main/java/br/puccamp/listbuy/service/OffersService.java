package br.puccamp.listbuy.service;

import br.puccamp.listbuy.dao.ListDAO;
import br.puccamp.listbuy.dao.OffersDAO;
import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.entities.Ofertas;

import java.util.List;

public class OffersService {

    OffersDAO offersDAO = new OffersDAO();

    public List<Ofertas> listarOfertas() {
        List<Ofertas> ofertas = offersDAO.listarOfertas();
        if (ofertas == null) {
            throw new RuntimeException("Nenhuma oferta!");
        }
        return ofertas;
    }

}
