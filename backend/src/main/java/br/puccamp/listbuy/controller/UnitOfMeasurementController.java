package br.puccamp.listbuy.controller;

import br.puccamp.listbuy.service.*;
import br.puccamp.listbuy.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UnitOfMeasurementController {
    UnitOfMeasurementService unitOfMeasurementService = new UnitOfMeasurementService();

    @RequestMapping("/infos/UnitOfMeasurement/")
    public ResponseEntity<Response> listarUnidadesMedida() {
        try {
            return new ResponseEntity<>(new Response<>("success", unitOfMeasurementService.listarUnidadesMedida()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("Erro ao buscar unidades de medida"), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("/infos/UnitOfMeasurement/unit")
    public ResponseEntity<List<String>> listarSiglasUnidadesMedida() {
            return new ResponseEntity<>(unitOfMeasurementService.listarSiglasUnidadesMedida(), HttpStatus.OK);
    }
}
