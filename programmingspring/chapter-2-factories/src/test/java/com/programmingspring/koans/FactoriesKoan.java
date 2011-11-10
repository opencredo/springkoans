package com.programmingspring.koans;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoriesKoan extends TestCase {

    public void testKoan1GettingSingletonFromApplicationContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("gof-singleton-context.xml");
        assertNotNull("Please configure Singleton as a bean in the ApplicationContext, with id = 'singletonInstance'", 
        		applicationContext.getBean("singletonInstance", Singleton.class));
    }

    public void testKoan2GettingStaticFactoryProduct() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("static-factory-context.xml");

        Product product = applicationContext.getBean("staticFactoryProduct", Product.class);
        assertNotNull("Please configure StaticFactory in the ApplicationContext, with id='staticFactoryProduct'", 
        		product);
        assertEquals("Please configure StaticFactory in the ApplicationContext so that it produces a Product named 'someProductName'", 
        		"someProductName", product.getProductName());
    }
    
    public void testKoan3GettingPojoFactoryProduct() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("pojo-factory-context.xml");

        Product product = applicationContext.getBean("pojoFactoryProduct", Product.class);
        assertNotNull("Please configure PojoFactory in the ApplicationContext, with id='pojoFactoryProduct'", 
        		product);
        assertEquals("Please configure PojoFactory in the ApplicationContext so that it produces a Product named 'anotherProductName'", 
        		"anotherProductName", product.getProductName());
    }

    public void testKoan4GettingSpringAwareFactoryProduct() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aware-factory-context.xml");

        Product product = applicationContext.getBean("springFactoryBeanProduct", Product.class);
        assertNotNull("Please configure SpringAwareFactory in the ApplicationContext, with id='springFactoryBeanProduct'", 
        		product);
        assertEquals("Please configure SpringAwareFactory in the ApplicationContext, with id='springFactoryBeanProduct'", 
        		"alwaysTheSame", product.getProductName());
    }
}
