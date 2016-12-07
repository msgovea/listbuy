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
    RegisterService registerService = new RegisterService();

    @ResponseBody
    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Response<Consumidor>> efetuarRegistro(@RequestBody Consumidor dados) {
        try {
            return new ResponseEntity<>(new Response<>("success", registerService.efetuarCadastro(dados)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("Dados incorretos!" + e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("/activate/{keyAcesso}")
    public ResponseEntity<String> listasPorUsuario(@PathVariable("keyAcesso") String keyAcesso) {
            return new ResponseEntity<>(registerService.ativarConta(keyAcesso), HttpStatus.OK);
    }
}
