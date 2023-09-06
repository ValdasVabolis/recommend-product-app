package com.seb.valdas.productrecommendationservice.products.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    public List<String> recommend(Boolean isStudent, String ageRange, String incomeBracket) {
        int income = parseRange(incomeBracket);
        int age = parseRange(ageRange);
        List<String> products = new ArrayList<>();
        if (age < 18) {
            products.add("Junior Saver Account");
        }
        if (age > 17) {
            if (income > 0) {
                products.add("Current Account");
            }
            if (income > 12000) {
                products.add("Credit Card");
            }
            if (income > 40000) {
                products.add("Current Account Plus");
                products.add("Gold Credit Card");
            }
            if (income < 12001) {
                products.add("Debit Card");
            }
            if (isStudent) {
                products.add("Student Account");
            }
        }
        if (age >= 65) {
            products.add("Senior Account");
        }
        return products;
    }

    private Integer parseRange(String ageRange) {
        if (ageRange.indexOf('-') != -1) {
            return Integer.parseInt(ageRange.split("-")[1]);
        }
        return Integer.parseInt(ageRange);
    }
}
