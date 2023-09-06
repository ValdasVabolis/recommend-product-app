package com.seb.valdas.productrecommendationservice.products.controller;

import com.seb.valdas.productrecommendationservice.products.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductsController {
    private final ProductsService productsService;

    ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/recommend")
    List<String> recommendProducts(@RequestParam(name = "isStudent") Boolean isStudent, @RequestParam("age") String age, @RequestParam("income") String incomeBracket) {
        return productsService.recommend(isStudent, age, incomeBracket);
    }
}
