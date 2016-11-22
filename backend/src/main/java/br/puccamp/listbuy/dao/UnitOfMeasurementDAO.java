package br.puccamp.listbuy.dao;


import br.puccamp.listbuy.entities.Consumidor;
import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.entities.Produtos;
import br.puccamp.listbuy.entities.UnidadeMedida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitOfMeasurementDAO extends GenericDAO {

    public ArrayList<UnidadeMedida> listarUnidadesMedida() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM UNIDADE_MEDIDA");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            ArrayList<UnidadeMedida> unidadesMedida = new ArrayList<>();
            Boolean achou = false;
            while (rs.next()) {
                UnidadeMedida unidadeMedida = new UnidadeMedida();
                unidadeMedida.setId_unidade_medida  (rs.getInt   (1));
                unidadeMedida.setDrescicao_unidade  (rs.getString(2));
                unidadeMedida.setAtiva              (rs.getString(3));
                unidadeMedida.setSigla              (rs.getString(4));

                unidadesMedida.add(unidadeMedida);
                achou = true;
            }
            if (achou) { return unidadesMedida; }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao encontrar as unidades de medida!", e);
        }
    }

    public List<String> listarSiglasUnidadesMedida() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT SIGLA FROM UNIDADE_MEDIDA ");
        sql.append("WHERE ATIVA = ?");
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1, "Y");
            ResultSet rs = stmt.executeQuery();
            List<String> siglasUnidadesMedida = new ArrayList<>();
            Boolean achou = false;
            while (rs.next()) {
                siglasUnidadesMedida.add(rs.getString(1));
                achou = true;
            }
            if (achou) { return siglasUnidadesMedida; }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao encontrar as unidades de medida!", e);
        }
    }
}
