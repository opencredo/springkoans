package com.programmingspring.koans;

public class Product {

    private final String productName;

    public Product(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product that = (Product) o;

        if (!productName.equals(that.productName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return productName.hashCode();
    }
}
