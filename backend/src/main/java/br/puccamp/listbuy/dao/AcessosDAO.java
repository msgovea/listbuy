package br.puccamp.listbuy.dao;


import br.puccamp.listbuy.entities.Consumidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AcessosDAO extends GenericDAO {

    public Consumidor registrarConsumidor(Consumidor acesso) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO CONSUMIDOR ");
        sql.append("(NOME, EMAIL, SENHA, ID_TIPO_ACESSO, KEY_ACESSO) ");
        sql.append("values (?,?,?,?,?) ");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString(), new String[]{"ID_CONSUMIDOR"});
            stmt.setString(1, acesso.getNome());
            stmt.setString(2, acesso.getEmail());
            stmt.setString(3, acesso.getSenha());
            stmt.setString(4, acesso.getId_tipo_acesso());
            stmt.setString(5, acesso.getKey_acesso());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            acesso.setId_consumidor(rs.getInt(1));
            return acesso;
        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException("Erro ao cadastrar consumidor!", e);
        }
    }

    public Consumidor efetuarLogin(Consumidor login) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM CONSUMIDOR ");
        sql.append("WHERE EMAIL = ? AND SENHA = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getSenha());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Consumidor retorno = new Consumidor();
                retorno.setId_consumidor(rs.getInt(1));
                retorno.setNome(rs.getString(2));
                retorno.setEmail(rs.getString(3));
                //retorno.setSenha(rs.getString(4));
                retorno.setId_tipo_acesso(rs.getString(5));
                retorno.setKey_acesso(rs.getString(6));
                return retorno;

            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Usuário ou senha inválido!", e);
        }
    }
}
