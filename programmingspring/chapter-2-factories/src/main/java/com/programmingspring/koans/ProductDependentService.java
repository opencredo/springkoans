package com.programmingspring.koans;

public class ProductDependentService {

    private final Product product;

    public ProductDependentService(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
