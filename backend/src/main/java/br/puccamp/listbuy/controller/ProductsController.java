package br.puccamp.listbuy.controller;

import br.puccamp.listbuy.service.ProductService;
import br.puccamp.listbuy.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
    ProductService productService = new ProductService();

    @RequestMapping("/list/productsByList/{codigoLista}/")
    public ResponseEntity<Response> listasPorUsuario(@PathVariable("codigoLista") int idLista) {
        try {
            return new ResponseEntity<>(new Response<>("success", productService.listarProdutosPorLista(idLista)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("A lista não tem nenhum produto"), HttpStatus.UNAUTHORIZED);
        }
    }
}
