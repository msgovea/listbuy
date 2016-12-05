package br.puccamp.listbuy.dao;


import br.puccamp.listbuy.entities.Consumidor;
import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.entities.Produtos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends GenericDAO {

    public List<Produtos> listarInformacoesLista (Long idLista) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM PRODUTOS PROD, LISTAS_X_PRODUTOS PL, PRODUTO_CADASTRADO PC ");
        sql.append("WHERE PROD.ID_PRODUTO = PL.ID_PRODUTO AND PROD.ID_PRODUTO = PC.ID_PRODUTO ");
        sql.append("AND PL.ID_LISTA = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setLong(1, idLista);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Produtos> produtos = new ArrayList<>();
            while (rs.next()) {
                Produtos retorno = new Produtos();
                retorno.setId_produto(rs.getLong(1));
                retorno.setNome(rs.getString(2));
                retorno.setDescricao(rs.getString(3));
                retorno.setId_categoria(rs.getLong(4));
                retorno.setAtivo(rs.getString(5));
                retorno.setId_lista(rs.getLong(6));
                retorno.setQuantidade(rs.getLong(8));
                retorno.setId_unidade_medida(rs.getLong(9));
                retorno.setId_consumidor(rs.getLong(10));
                retorno.setData_cadastro(rs.getDate(12).toString());
                produtos.add(retorno);
            }
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException("Produto não encontrado!", e);
        }
    }

    public Produtos atualizarProdutos(Produtos dados) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE PRODUTOS ");
        sql.append("SET NOME = ?, DESCRICAO = ? ");
        sql.append("WHERE ID_PRODUTO = ?");

        StringBuilder sql2 = new StringBuilder();
        sql2.append("UPDATE LISTAS_X_PRODUTOS ");
        sql2.append("SET QUANTIDADE = ? ");
        sql2.append("WHERE ID_PRODUTO = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1, dados.getNome());
            stmt.setString(2, dados.getDescricao());
            stmt.setLong(3, dados.getId_produto());
            stmt.executeUpdate();

            stmt = connection.prepareStatement(sql2.toString());
            stmt.setLong(1, dados.getQuantidade());
            stmt.setLong(2, dados.getId_produto());
            stmt.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto!", e);
        }
        return dados;
    }

    public Produtos registrarProdutos(Produtos dados) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PRODUTOS ");
        sql.append("(NOME, DESCRICAO, ID_CATEGORIA, ATIVO) ");
        sql.append("values (?,?,?,?) ");

        StringBuilder sql2 = new StringBuilder();
        sql2.append("INSERT INTO PRODUTO_CADASTRADO ");
        sql2.append("(ID_CONSUMIDOR, ID_PRODUTO, DATA_CADASTRO) ");
        sql2.append("VALUES (?, ?, SYSDATE)");

        StringBuilder sql3 = new StringBuilder();
        sql3.append("INSERT INTO LISTAS_X_PRODUTOS ");
        sql3.append("(ID_LISTA, ID_PRODUTO, QUANTIDADE, ID_UNIDADE_MEDIDA)");
        sql3.append("VALUES (?, ?, ?, ?)");

        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString(), new String[]{"ID_PRODUTO"});
            stmt.setString(1, dados.getNome());
            stmt.setString(2, dados.getDescricao());
            stmt.setLong  (3, dados.getId_categoria());
            stmt.setString(4, dados.getAtivo());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            dados.setId_produto(rs.getLong(1));

            stmt = connection.prepareStatement(sql2.toString());
            stmt.setLong(1, dados.getId_consumidor());
            stmt.setLong(2, dados.getId_produto());
            stmt.execute();

            stmt = connection.prepareStatement(sql3.toString());
            stmt.setLong(1, dados.getId_lista());
            stmt.setLong(2, dados.getId_produto());
            stmt.setLong(3, dados.getQuantidade());
            stmt.setLong(4, dados.getId_unidade_medida());
            stmt.execute();

            connection.commit();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Erro ao cadastrar produto!", e);
        }

        return dados;
    }

}
