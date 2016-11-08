package br.puccamp.listbuy.dao;


import br.puccamp.listbuy.entities.Consumidor;
import br.puccamp.listbuy.entities.Listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListDAO extends GenericDAO {

    public void inserirLista(Consumidor acesso) {
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

    public ArrayList<Listas> listarListasPorUsuario(int idUsuario) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM LISTAS ");
        sql.append("WHERE ID_CONSUMIDOR = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Listas> lista = new ArrayList<>();
            Boolean achou = false;
            while (rs.next()) {
                Listas retorno = new Listas();
                retorno.setId_lista(rs.getInt(1));
                retorno.setTipo_lista(rs.getString(2));
                retorno.setTitulo(rs.getString(3));
                retorno.setId_consumidor(rs.getInt(4));
                retorno.setAtiva(rs.getString(5));
                lista.add(retorno);
                achou = true;
            }
            if (achou) { return lista; }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Usuário ou senha inválido!", e);
        }
    }
}
