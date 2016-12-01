package br.puccamp.listbuy.controller;

import br.puccamp.listbuy.service.OffersService;
import br.puccamp.listbuy.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

}
