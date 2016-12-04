package br.puccamp.listbuy.service;

import br.puccamp.listbuy.dao.ListDAO;
import br.puccamp.listbuy.dao.OffersDAO;
import br.puccamp.listbuy.entities.Consumidor;
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

    public Ofertas inserirOfertas(Ofertas oferta) {
        Ofertas ofertas = offersDAO.inserirOferta(oferta);
        if (ofertas == null) {
            throw new RuntimeException("Email ou senha inválido!");
        }
        return ofertas;
    }

}
