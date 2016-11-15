package br.puccamp.listbuy.service;


import br.puccamp.listbuy.dao.AcessosDAO;
import br.puccamp.listbuy.entities.Consumidor;

import java.util.ArrayList;

public class RegisterService {

    AcessosDAO acessosDAO = new AcessosDAO();

    public Consumidor efetuarCadastro(Consumidor login) {
        Consumidor acessos = acessosDAO.registrarConsumidor(login);
        if (acessos == null) {
            throw new RuntimeException("Email ou senha inválido!");
        }
        return acessos;
    }

}
