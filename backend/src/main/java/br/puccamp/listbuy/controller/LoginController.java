package br.puccamp.listbuy.controller;

import br.puccamp.listbuy.entities.Acessos;
import br.puccamp.listbuy.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {
    LoginService loginService = new LoginService();

    @ResponseBody
    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Response<Acessos>> efetuarLogin(@RequestBody Acessos login) {
        try {
            return new ResponseEntity<>(new Response<>("success", loginService.efetuarLogin(login)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("Dados incorretos!"), HttpStatus.FORBIDDEN);
        }
    }
}
