package br.puccamp.listbuy.controller;

import br.puccamp.listbuy.entities.Listas;
import br.puccamp.listbuy.entities.Produtos;
import br.puccamp.listbuy.service.ProductService;
import br.puccamp.listbuy.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsController {
    ProductService productService = new ProductService();

    @RequestMapping("/list/productsByList/{codigoLista}/")
    public ResponseEntity<Response> listasPorUsuario(@PathVariable("codigoLista") Long idLista) {
        try {
            return new ResponseEntity<>(new Response<>("success", productService.listarProdutosPorLista(idLista)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("A lista não tem nenhum produto" + e), HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/product/update/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Response<Produtos>> atualizarProduto(@RequestBody Produtos produto) {
        try {
            return new ResponseEntity<>(new Response<>("success", productService.atualizarProdutos(produto)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("Dados incorretos!" + e), HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/product/insert/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Response<Produtos>> registrarProduto(@RequestBody Produtos produto) {
        try {
            return new ResponseEntity<>(new Response<>("success", productService.registrarProduto(produto)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>("Dados incorretos!" + e), HttpStatus.UNAUTHORIZED);
        }
    }
}
