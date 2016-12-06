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
    public ResponseEntity<Response> listasPorUsuario(@PathVariable("idUsuario") Long idUsuario) {
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

    public ResponseEntity<Response<Listas>> atualizarLista(@RequestBody Listas dados) {
        try {
            return new ResponseEntity<>(new Response<>("success", listService.atualizarLista(dados)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("Dados incorretos!" + e), HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/list/insert/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Response<Listas>> inserirLista(@RequestBody Listas dados) {
        try {
            return new ResponseEntity<>(new Response<>("success", listService.inserirLista(dados)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("Dados incorretos!" + e), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("/list/delete/{idList}/")
    public ResponseEntity<Response> deletarLista(@PathVariable("idList") Long idList) {
        try {
            listService.deletarLista(idList);
            return new ResponseEntity<>(new Response<>("success"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

}
