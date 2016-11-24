package br.puccamp.listbuy.service;


import br.puccamp.listbuy.dao.AcessosDAO;
import br.puccamp.listbuy.entities.Consumidor;
import br.puccamp.listbuy.utils.Hash;
import br.puccamp.listbuy.utils.Rest;

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

        try {
            String url = "http://servidor.listbuy.me/EnviaEmail/default.aspx"
                    + "?destinatario=" + acessos.getEmail()
                    + "&keyAcesso=" + acessos.getKey_acesso()
                    + "&nome='" + acessos.getNome() + "'";
            url = url.replace(" ", "%20");

            String result = Rest.sendGet(url);
            if ((result.compareTo("sucesso") != 0))
            {
                throw new RuntimeException(result);
                //TODO: IMPLEMENTAR ROLLBACK DA CRIAÇÃO DO USUÁRIO
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return acessos;
    }

    public Boolean ativarConta(String keyAcesso) {
        return acessosDAO.ativarConta(keyAcesso);
    }

}
