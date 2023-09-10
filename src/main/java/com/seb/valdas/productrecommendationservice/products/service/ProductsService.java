package com.seb.valdas.productrecommendationservice.products.service;

import com.seb.valdas.productrecommendationservice.products.dto.Range;
import com.seb.valdas.productrecommendationservice.products.dto.ProductDTO;
import com.seb.valdas.productrecommendationservice.products.entity.Product;
import com.seb.valdas.productrecommendationservice.products.repository.ProductRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    private final ProductRepository productRepository;

    ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> recommend(Boolean isStudent, String ageRange, String incomeBracket) {
        Range range = parseRange(incomeBracket);
        Range age = parseRange(ageRange);
        List<Product> products = productRepository.findAll();

        return products.stream()
                .filter(p -> p.matchesCriteria(age, range, isStudent))
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO productDTO) throws Exception {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setMaxAge(productDTO.getMaxAge());
        product.setMinAge(productDTO.getMinAge());
        product.setMinIncome(productDTO.getMinIncome());
        product.setMaxIncome(productDTO.getMaxIncome());
        product.setForStudent(productDTO.getForStudent());
        try {
            productRepository.save(product);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new Exception("Could not save product: ");
        }
        return productDTO;
    }

    private Range parseRange(String ageRange) {
        Range parsedRange = new Range();
        String range = ageRange.replaceAll("[^0-9-+]", "");

        if (range.endsWith("+")) {
            range = range.substring(0, range.length() - 1);
            parsedRange.setLowerBound(Long.valueOf(range));
            return parsedRange;
        }

        if (range.indexOf('-') != -1) {
            parsedRange.setLowerBound(Long.valueOf(range.split("-")[0]));
            parsedRange.setUpperBound(Long.valueOf(range.split("-")[1]));
            return parsedRange;
        }

        parsedRange.setLowerBound(Long.valueOf(range));
        return parsedRange;
    }
}
