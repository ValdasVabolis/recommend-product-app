package com.seb.valdas.productrecommendationservice.products.controller;

import com.seb.valdas.productrecommendationservice.products.dto.ProductDTO;
import com.seb.valdas.productrecommendationservice.products.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductsController {
    private final ProductsService productsService;

    ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/recommend")
    List<ProductDTO> recommendProducts(@RequestParam(name = "isStudent") Boolean isStudent, @RequestParam("age") String age, @RequestParam("income") String incomeBracket) {
        return productsService.recommend(isStudent, age, incomeBracket);
    }

    @PostMapping("/add")
    ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.ok(productsService.createProduct(productDTO));
        } catch (Exception e) {
            System.err.println("Could not add product" + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all")
    ResponseEntity<List<ProductDTO>> getAllProducts() {
        try {
            return ResponseEntity.ok(productsService.findAll());
        } catch (Exception e) {
            System.err.println("Could not fetch products" + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
