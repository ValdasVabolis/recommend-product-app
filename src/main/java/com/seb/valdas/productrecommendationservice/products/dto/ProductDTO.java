package com.seb.valdas.productrecommendationservice.products.dto;

import com.seb.valdas.productrecommendationservice.products.entity.Product;

public class ProductDTO {
    private String name;
    private Long minAge;
    private Long maxAge;
    private Long minIncome;

    private Long maxIncome;
    private Boolean forStudent;

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.minAge = product.getMinAge();
        this.maxAge = product.getMaxAge();
        this.minIncome = product.getMinIncome();
        this.maxIncome = product.getMaxIncome();
        this.forStudent = product.getForStudent();
    }

    public ProductDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMinAge() {
        return minAge;
    }

    public void setMinAge(Long minAge) {
        this.minAge = minAge;
    }

    public Long getMinIncome() {
        return minIncome;
    }

    public void setMinIncome(Long minIncome) {
        this.minIncome = minIncome;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }

    public Long getMaxIncome() {
        return maxIncome;
    }

    public void setMaxIncome(Long maxIncome) {
        this.maxIncome = maxIncome;
    }

    public Boolean getForStudent() {
        return forStudent;
    }

    public void setForStudent(Boolean forStudent) {
        this.forStudent = forStudent;
    }
}
