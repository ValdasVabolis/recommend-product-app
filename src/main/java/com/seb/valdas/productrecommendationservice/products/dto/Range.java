package com.seb.valdas.productrecommendationservice.products.dto;

public class Range {
    private Long lowerBound;
    private Long upperBound;

    public Long getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Long lowerBound) {
        this.lowerBound = lowerBound;
    }

    public Long getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Long upperBound) {
        this.upperBound = upperBound;
    }
}
