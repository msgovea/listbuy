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
            stmt.setString(1, acesso.getNOME());
            stmt.setString(2, acesso.getEMAIL());
            stmt.setString(3, acesso.getSENHA());
            stmt.setString(4, acesso.getID_TIPO_ACESSO());
            stmt.setString(5, acesso.getKEY_ACESSO());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            acesso.setID_CONSUMIDOR(rs.getInt(1));
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
            stmt.setString(1, login.getEMAIL());
            stmt.setString(2, login.getSENHA());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Consumidor retorno = new Consumidor();
                retorno.setID_CONSUMIDOR(rs.getInt(1));
                retorno.setNOME(rs.getString(2));
                retorno.setEMAIL(rs.getString(3));
                //retorno.setSenha(rs.getString(4));
                retorno.setID_TIPO_ACESSO(rs.getString(5));
                retorno.setKEY_ACESSO(rs.getString(6));
                return retorno;

            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Usuário ou senha inválido!", e);
        }
    }
}
