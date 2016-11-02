package br.puccamp.listbuy.dao;


import br.puccamp.listbuy.entities.Acessos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcessosDAO extends GenericDAO {

    public void inserirAcesso(Acessos acesso) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into consumidor ");
        sql.append("(NOME, EMAIL, SENHA, ID_TIPO_ACESSO, KEY_ACESSO) ");
        sql.append("values (?,?,?,?,?) ");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString(), new String[]{"ID_CONSUMIDOR"});
            stmt.setString(1, acesso.getEmail());
            stmt.setString(2, acesso.getSenha());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            acesso.setId_consumidor(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar consumidor!", e);
        }
    }

    public Acessos efetuarLogin(Acessos login) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from consumidor ");
        sql.append("where email = ? AND senha = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getSenha());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return login;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Usuário ou senha inválido!", e);
        }
    }
}
