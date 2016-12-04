package br.puccamp.listbuy.dao;


import br.puccamp.listbuy.entities.Consumidor;
import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.entities.Produtos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListDAO extends GenericDAO {

    //TODO: DONE
    public Listas inserirLista(Listas lista) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into LISTAS ");
        sql.append("(TIPO_LISTA, TITULO, ID_CONSUMIDOR, ATIVA) ");
        sql.append("values (?,?,?,?) ");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString(), new String[]{"ID_LISTA", "DATA_ICS", "DATA_ALT"});
            stmt.setString(1, lista.getTipo_lista());
            stmt.setString(2, lista.getTitulo());
            stmt.setLong(3, lista.getId_consumidor());
            stmt.setString(4, lista.getAtiva());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            lista.setId_lista(rs.getLong(1));
            lista.setData_ics(rs.getDate(2));
            lista.setData_alt(rs.getDate(3));
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar lista!", e);
        }
    }

    public Listas listarInformacoesListas(int idLista) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM LISTAS ");
        sql.append("WHERE ID_LISTA = ? AND ATIVA <> 'N'");
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

//    public ArrayList<Produtos> listarProdutosPorLista(int idLista) {
//        StringBuilder sql = new StringBuilder();
//        sql.append("SELECT * FROM LISTAS_X_PRODUTOS ");
//        sql.append("WHERE ID_LISTA = ? AND ATIVA <> 'N'");
//        try (Connection connection = getConnection()) {
//            PreparedStatement stmt = connection.prepareStatement(sql.toString());
//            stmt.setInt(1, idLista);
//            ResultSet rs = stmt.executeQuery();
//            ArrayList<Produtos> produtos = new ArrayList<>();
//            Boolean achou = false;
//            while (rs.next()) {
//                ProductDAO productDAO = new ProductDAO();
//                Produtos produto = productDAO.listarInformacoesProdutos(rs.getLong(2)); //ID_PRODUTO
//                produtos.add(produto);
//                achou = true;
//            }
//            if (achou) {
//                return produtos;
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new RuntimeException("A lista não tem nenhum produto!", e);
//        }
//    }

    //TODO: OK
    public ArrayList<Listas> listarListasPorUsuario(int idUsuario) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM LISTAS ");
        sql.append("WHERE ID_CONSUMIDOR = ? AND ATIVA <> 'N'");
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
                retorno.setData_ics(rs.getDate(6));
                retorno.setData_alt(rs.getDate(7));
                lista.add(retorno);
                achou = true;
            }
            if (achou) {
                return lista;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Usuário ou senha inválido!", e);
        }
    }

    public Listas atualizarLista(Listas dados) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE LISTAS ");
        sql.append("SET TITULO = ?, DATA_ALT = SYSDATE ");
        sql.append("WHERE ID_LISTA = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1, dados.getTitulo());
            stmt.setLong(2, dados.getId_lista());
            //stmt.executeUpdate();
            stmt.executeUpdate();
            connection.commit();
            return dados;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar lista!", e);
        }
    }


    public void inserirListaEvento(Listas lista) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO LISTAS_EVENTOS ");
        sql.append("(ID_LISTA) ");
        sql.append("values (?)");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setLong(1, lista.getId_lista());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar lista!", e);
        }
    }

    public void inserirListaPessoal(Listas lista) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO LISTAS_PESSOAIS ");
        sql.append("(ID_LISTA, COMPARTILHADA) ");
        sql.append("values (?, 'N')");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setLong(1, lista.getId_lista());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar lista!!", e);
        }
    }

}