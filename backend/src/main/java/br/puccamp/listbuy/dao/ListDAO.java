package br.puccamp.listbuy.dao;


import br.puccamp.listbuy.entities.Consumidor;
import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.entities.Produtos;

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
            acesso.setId_consumidor(rs.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar consumidor!", e);
        }
    }

    public Listas listarInformacoesListas (int idLista) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM LISTAS ");
        sql.append("WHERE ID_LISTA = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setInt(1, idLista);
            ResultSet rs = stmt.executeQuery();
            Listas retorno = new Listas();
            if (rs.next()) {
                retorno.setId_lista(rs.getLong(1));
                retorno.setTipo_lista(rs.getString(2));
                retorno.setTitulo(rs.getString(3));
                retorno.setId_consumidor(rs.getLong(4));
                retorno.setAtiva(rs.getString(5));
                retorno.setData_ics(rs.getDate(6));
                retorno.setData_alt(rs.getDate(7));
                return retorno;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Produto não encontrado!", e);
        }
    }

    public ArrayList<Produtos> listarProdutosPorLista(int idLista) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM LISTAS_X_PRODUTOS ");
        sql.append("WHERE ID_LISTA = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setInt(1, idLista);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Produtos> produtos = new ArrayList<>();
            Boolean achou = false;
            while (rs.next()) {
                ProductDAO productDAO = new ProductDAO();
                Produtos produto = productDAO.listarInformacoesProdutos(rs.getLong(2)); //ID_PRODUTO
                produtos.add(produto);
                achou = true;
            }
            if (achou) { return produtos; }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("A lista não tem nenhum produto!", e);
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
                retorno.setId_lista(rs.getLong(1));
                retorno.setTipo_lista(rs.getString(2));
                retorno.setTitulo(rs.getString(3));
                retorno.setId_consumidor(rs.getLong(4));
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
