package br.puccamp.listbuy.dao;


import br.puccamp.listbuy.entities.Consumidor;
import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.entities.Ofertas;
import br.puccamp.listbuy.entities.Produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OffersDAO extends GenericDAO {

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
            acesso.setId_consumidor(rs.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar consumidor!", e);
        }
    }

    public ArrayList<Ofertas> listarOfertas() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM OFERTAS ");
        sql.append("WHERE ATIVA = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1, "Y");
            ResultSet rs = stmt.executeQuery();
            ArrayList<Ofertas> ofertas = new ArrayList<>();
            Boolean achou = false;
            while (rs.next()) {
                Ofertas retorno = new Ofertas();
                retorno.setId_oferta(rs.getLong(1));
                retorno.setDescricao(rs.getString(2));
                retorno.setNome(rs.getString(3));
                retorno.setImagem(rs.getBlob(4));
                retorno.setId_consumidor(rs.getLong(5));
                retorno.setAtiva(rs.getString(6));
                ofertas.add(retorno);
                achou = true;
            }
            if (achou) { return ofertas; }
            return null;
        } catch (SQLException e) {
            System.err.println("Erro Ofertas: " + e);
            throw new RuntimeException("Não há ofertas!!");
        }
    }

}
