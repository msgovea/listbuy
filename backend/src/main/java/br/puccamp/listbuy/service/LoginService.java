package br.puccamp.listbuy.service;


import br.puccamp.listbuy.dao.AcessosDAO;
import br.puccamp.listbuy.entities.Acessos;

public class LoginService {

    AcessosDAO acessosDAO = new AcessosDAO();

    public Acessos efetuarLogin(Acessos login) {
        Acessos acessos = acessosDAO.efetuarLogin(login);
        if (acessos == null) {
            throw new RuntimeException("Email ou senha inválido!");
        }
        return acessos;
    }

}
