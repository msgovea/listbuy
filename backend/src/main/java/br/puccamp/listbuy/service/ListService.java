package br.puccamp.listbuy.service;


import br.puccamp.listbuy.dao.AcessosDAO;
import br.puccamp.listbuy.dao.ListDAO;
import br.puccamp.listbuy.entities.Consumidor;
import br.puccamp.listbuy.entities.Listas;

import java.util.List;

public class ListService {

    ListDAO listDAO = new ListDAO();

    public List<Listas> listarListasPorUsuario(int idUsuario) {
        List<Listas> listas = listDAO.listarListasPorUsuario(idUsuario);
        if (listas == null) {
            throw new RuntimeException("Email ou senha inválido!");
        }
        return listas;
    }

}
