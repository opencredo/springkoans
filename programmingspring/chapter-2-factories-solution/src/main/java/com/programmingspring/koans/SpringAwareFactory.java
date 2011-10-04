package com.programmingspring.koans;

import org.springframework.beans.factory.FactoryBean;

public class SpringAwareFactory implements FactoryBean<Product> {

    @Override
    public Product getObject() throws Exception {
        return new Product("alwaysTheSame");
    }

    @Override
    public Class<?> getObjectType() {
        return Product.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
