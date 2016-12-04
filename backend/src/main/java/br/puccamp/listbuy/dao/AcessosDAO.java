package br.puccamp.listbuy.dao;


import br.puccamp.listbuy.entities.Consumidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            acesso.setId_consumidor(rs.getLong(1));
            return acesso;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Erro ao cadastrar consumidor!", e);
        }
    }

    public Consumidor efetuarLogin(Consumidor login) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM CONSUMIDOR ");
        sql.append("WHERE EMAIL = ? AND SENHA = ? AND TIPO_ACESSO <> 'B'");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getSenha());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Consumidor retorno = new Consumidor();
                retorno.setId_consumidor (rs.getLong  (1));
                retorno.setNome          (rs.getString(2));
                retorno.setEmail         (rs.getString(3));
                retorno.setId_tipo_acesso(rs.getString(5));
                retorno.setKey_acesso    (rs.getString(6));
                return retorno;

            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Usuário ou senha inválido!", e);
        }
    }

    public Boolean ativarConta(String keyAcesso) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE CONSUMIDOR ");
        sql.append("SET ID_TIPO_ACESSO = 'N' ");
        sql.append("WHERE ID_TIPO_ACESSO = 'A' AND KEY_ACESSO = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1, keyAcesso);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro cadastro: " + e);
            throw new RuntimeException("Erro ao ativar cadastro!", e);
        }
    }
}
