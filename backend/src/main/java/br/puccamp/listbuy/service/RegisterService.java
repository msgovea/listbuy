package br.puccamp.listbuy.service;


import br.puccamp.listbuy.dao.AcessosDAO;
import br.puccamp.listbuy.entities.Consumidor;

import java.util.ArrayList;

public class RegisterService {

    AcessosDAO acessosDAO = new AcessosDAO();

    public Consumidor efetuarLogin(Consumidor login) {
        Consumidor acessos = acessosDAO.inserirAcesso(login);
        if (acessos == null) {
            throw new RuntimeException("Email ou senha inválido!");
        }
        return acessos;
    }

}
