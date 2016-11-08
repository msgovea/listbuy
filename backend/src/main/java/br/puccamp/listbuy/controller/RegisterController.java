package br.puccamp.listbuy.controller;

import br.puccamp.listbuy.entities.Consumidor;
import br.puccamp.listbuy.service.LoginService;
import br.puccamp.listbuy.service.RegisterService;
import br.puccamp.listbuy.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.spi.RegisterableService;
import java.util.ArrayList;


@RestController
public class RegisterController {
    RegisterService loginService = new RegisterService();

    @ResponseBody
    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Response<Consumidor>> efetuarLogin(@RequestBody Consumidor dados) {
        try {
            return new ResponseEntity<>(new Response<>("success", loginService.efetuarLogin(dados)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("Dados incorretos!"), HttpStatus.UNAUTHORIZED);
        }
    }
}
