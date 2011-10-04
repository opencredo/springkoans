package com.programmingspring.koans;

public class StaticFactory {

    public static Product createProduct(String productName) {
        return new Product(productName);
    }
}
