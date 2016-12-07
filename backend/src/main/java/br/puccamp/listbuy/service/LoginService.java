package br.puccamp.listbuy.service;


import br.puccamp.listbuy.dao.AcessosDAO;
import br.puccamp.listbuy.entities.Consumidor;

import java.util.ArrayList;

public class LoginService {

    AcessosDAO acessosDAO = new AcessosDAO();

    public Consumidor efetuarLogin(Consumidor login) {
        Consumidor acessos = acessosDAO.efetuarLogin(login);
        if (acessos == null) {
            throw new RuntimeException("Email ou senha inválido!");
        }
        if (acessos.getId_tipo_acesso().compareTo("A") == 0) {
            throw new RuntimeException("ativacao");
        }
        return acessos;
    }

}
