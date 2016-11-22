package br.puccamp.listbuy.service;

import br.puccamp.listbuy.dao.*;
import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.entities.UnidadeMedida;

import java.util.List;

public class UnitOfMeasurementService {

    UnitOfMeasurementDAO unitofMeasurementDAO = new UnitOfMeasurementDAO();

    public List<UnidadeMedida> listarUnidadesMedida() {
        List<UnidadeMedida> unidades = unitofMeasurementDAO.listarUnidadesMedida();
        if (unidades == null) {
            throw new RuntimeException("Erro ao buscar unidades!");
        }
        return unidades;
    }

    public List<String> listarSiglasUnidadesMedida() {
        List<String> siglas = unitofMeasurementDAO.listarSiglasUnidadesMedida();
        if (siglas == null) {
            throw new RuntimeException("Erro ao buscar siglas de unidades de medidas!");
        }
        return siglas;
    }
}
