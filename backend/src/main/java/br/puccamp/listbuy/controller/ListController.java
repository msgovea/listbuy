package br.puccamp.listbuy.controller;

import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.service.ListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ListController {
    ListService listService = new ListService();

    @RequestMapping("/list/user/{idUsuario}/")
    public ResponseEntity<List<Listas>> listasPorUsuario(@PathVariable("idUsuario") int idUsuario) {
        return new ResponseEntity<>(listService.listarListasPorUsuario(idUsuario), HttpStatus.OK);
    }

//    @RequestMapping("/list/oferts/")
//    public ResponseEntity<List<String>> listarOfertas() {
//        return new ResponseEntity<>(OfertsService.listarOfertas(), HttpStatus.OK);
//    }

}
