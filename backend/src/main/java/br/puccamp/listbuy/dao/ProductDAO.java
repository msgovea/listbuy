package br.puccamp.listbuy.dao;


import br.puccamp.listbuy.entities.Produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO extends GenericDAO {

    public Produtos listarInformacoesProdutos (Long idProduto) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM PRODUTOS ");
        sql.append("WHERE ID_PRODUTO = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setLong(1, idProduto);
            ResultSet rs = stmt.executeQuery();
            Produtos retorno = new Produtos();
            if (rs.next()) {
                retorno.setId_produto(rs.getLong(1));
                retorno.setNome(rs.getString(2));
                retorno.setDescricao(rs.getString(3));
                retorno.setId_categoria(rs.getLong(4));
                retorno.setAtivo(rs.getString(5));
                return retorno;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Produto não encontrado!", e);
        }
    }


}
