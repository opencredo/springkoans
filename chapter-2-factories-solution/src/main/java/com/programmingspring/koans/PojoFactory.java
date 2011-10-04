package com.programmingspring.koans;

public class PojoFactory {

    public Product createProduct(String productName) {
        return new Product(productName);
    }
}
