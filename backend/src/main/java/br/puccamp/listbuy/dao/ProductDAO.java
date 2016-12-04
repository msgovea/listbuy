package br.puccamp.listbuy.dao;


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


}
