package br.puccamp.listbuy.controller;

import br.puccamp.listbuy.entities.Consumidor;
import br.puccamp.listbuy.entities.Ofertas;
import br.puccamp.listbuy.service.OffersService;
import br.puccamp.listbuy.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OffersController {
    OffersService offersService = new OffersService();

    @RequestMapping("/offers/")
    public ResponseEntity<Response> listarOfertas() {
        try {
            return new ResponseEntity<>(new Response<>("success", offersService.listarOfertas()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("Não há ofertas!" + e), HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/offers/insert/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Response<Ofertas>> inserirOferta(@RequestBody Ofertas oferta) {
        try {
            return new ResponseEntity<>(new Response<>("success", offersService.inserirOfertas(oferta)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("Dados incorretos!" + e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

}
