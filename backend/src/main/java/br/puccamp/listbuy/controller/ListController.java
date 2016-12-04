package br.puccamp.listbuy.controller;

import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.service.ListService;
import br.puccamp.listbuy.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ListController {
    ListService listService = new ListService();

    @RequestMapping("/list/user/{idUsuario}/")
    public ResponseEntity<Response> listasPorUsuario(@PathVariable("idUsuario") int idUsuario) {
        try {
            return new ResponseEntity<>(new Response<>("success", listService.listarListasPorUsuario(idUsuario)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("O usuário não tem nenhuma lista"), HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/list/update/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Response<Listas>> efetuarLogin(@RequestBody Listas dados) {
        try {
            return new ResponseEntity<>(new Response<>("success", listService.atualizarLista(dados)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("Dados incorretos!" + e), HttpStatus.UNAUTHORIZED);
        }
    }

//    @RequestMapping("/list/oferts/")
//    public ResponseEntity<List<String>> listarOfertas() {
//        return new ResponseEntity<>(OfertsService.listarOfertas(), HttpStatus.OK);
//    }

}
