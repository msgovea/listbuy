package br.puccamp.listbuy.service;


import br.puccamp.listbuy.dao.AcessosDAO;
import br.puccamp.listbuy.entities.Consumidor;

public class LoginService {

    AcessosDAO acessosDAO = new AcessosDAO();

    public Consumidor efetuarLogin(Consumidor login) {
        Consumidor acessos = acessosDAO.efetuarLogin(login);
        if (acessos == null) {
            throw new RuntimeException("Email ou senha inválido!");
        }
        return acessos;
    }

}
