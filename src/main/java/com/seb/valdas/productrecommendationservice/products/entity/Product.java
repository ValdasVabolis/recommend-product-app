package com.seb.valdas.productrecommendationservice.products.entity;

import com.seb.valdas.productrecommendationservice.products.dto.Range;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Optional;

@Entity
@Table(name="PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="min_age")
    private Long minAge;

    @Column(name="max_age")
    private Long maxAge;

    @Column(name="min_income")
    private Long minIncome;

    @Column(name="max_income")
    private Long maxIncome;

    @Column(name="for_student")
    private Boolean forStudent;

    public Product() {

    }

    public String getName() {
        return name;
    }

    public Long getMinAge() {
        return Optional.ofNullable(this.minAge).orElse(Long.MIN_VALUE);
    }

    public Long getMaxAge() {
        return Optional.ofNullable(this.maxAge).orElse(Long.MAX_VALUE);
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }

    public Long getMinIncome() {
        return Optional.ofNullable(this.minIncome).orElse(Long.MIN_VALUE);
    }

    public Long getMaxIncome() {
        return Optional.ofNullable(this.maxIncome).orElse(Long.MAX_VALUE);
    }

    public void setMaxIncome(Long maxIncome) {
        this.maxIncome = maxIncome;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinAge(Long minAge) {
        this.minAge = minAge;
    }

    public void setMinIncome(Long minIncome) {
        this.minIncome = minIncome;
    }

    public void setForStudent(Boolean forStudent) {
        this.forStudent = forStudent;
    }

    public Boolean getForStudent() {
        return forStudent;
    }

    public Product(Long id, String name, Long minAge, Long maxAge, Long minIncome, Long maxIncome, Boolean forStudent) {
        this.id = id;
        this.name = name;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minIncome = minIncome;
        this.maxIncome = maxIncome;
        this.forStudent = forStudent;
    }

    private boolean ageRangeWithinBounds(Range age) {
        boolean lowerBoundOk = age.getLowerBound() == null || age.getLowerBound() >= this.getMinAge();
        boolean upperBoundOk = age.getUpperBound() == null || age.getUpperBound() <= this.getMaxAge();
        return lowerBoundOk && upperBoundOk;
    }

    private boolean incomeRangeWithinBounds(Range income) {
        boolean lowerBoundOk = income.getLowerBound() == null || income.getLowerBound() >= this.getMinIncome();
        boolean upperBoundOk = income.getUpperBound() == null || income.getUpperBound() <= this.getMaxIncome();
        return lowerBoundOk && upperBoundOk;
    }

    public boolean matchesCriteria(Range age, Range income, Boolean isStudent) {
        return !(this.forStudent && !isStudent) && ageRangeWithinBounds(age) && incomeRangeWithinBounds(income);
    }

}
