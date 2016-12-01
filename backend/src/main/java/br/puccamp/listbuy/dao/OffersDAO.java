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

    public Ofertas inserirOferta(Ofertas oferta) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO OFERTAS ");
        sql.append("(DESCRICAO, NOME, IMAGEM, ID_CONSUMIDOR, ATIVA) ");
        sql.append("values (?,?,?,?,?) ");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString(), new String[]{"ID_OFERTA"});
            stmt.setString(1, oferta.getDescricao());
            stmt.setString(2, oferta.getNome());
            stmt.setBlob  (3, oferta.getImagem());
            stmt.setLong  (4, oferta.getId_consumidor());
            stmt.setString(5, oferta.getAtiva());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            oferta.setId_consumidor(rs.getLong(1));
            return oferta;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Erro ao cadastrar oferta!", e);
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
