package br.puccamp.listbuy.service;


import br.puccamp.listbuy.dao.AcessosDAO;
import br.puccamp.listbuy.entities.Consumidor;
import br.puccamp.listbuy.utils.Hash;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class RegisterService {

    AcessosDAO acessosDAO = new AcessosDAO();

    public Consumidor efetuarCadastro(Consumidor login) {

        login.setKey_acesso(Hash.md5(login.getEmail()));

        Consumidor acessos = acessosDAO.registrarConsumidor(login);
        if (acessos == null) {
            throw new RuntimeException("Email ou senha inválido!");
        }
        return acessos;
    }

    public Boolean ativarConta(String keyAcesso) {
        return acessosDAO.ativarConta(keyAcesso);
    }

}
