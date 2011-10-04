package com.programmingspring.koans;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoriesKoan extends TestCase {

    public void testGettingSingletonFromApplicationContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factories-context.xml");
        assertNotNull("Please configure Singleton as a bean in the ApplicationContext, with id = 'singletonInstance'", applicationContext.getBean("singletonInstance", Singleton.class));
    }

    public void testGettingStaticFactoryProduct() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factories-context.xml");

        Product product = applicationContext.getBean("staticFactoryProduct", Product.class);
        assertNotNull("Please configure StaticFactory in the ApplicationContext, with id='staticFactoryProduct'", product);
        assertEquals("Please configure StaticFactory in the ApplicationContext so that it produces a Product named 'someProductName'", "someProductName", product.getProductName());
    }

    public void testGettingPojoFactoryProduct() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factories-context.xml");

        Product product = applicationContext.getBean("pojoFactoryProduct", Product.class);
        assertNotNull("Please configure PojoFactory in the ApplicationContext, with id='pojoFactoryProduct'", product);
        assertEquals("Please configure PojoFactory in the ApplicationContext so that it produces a Product named 'anotherProductName'", "anotherProductName", product.getProductName());
    }

    public void testGettingSpringAwareFactoryProduct() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factories-context.xml");

        Product product = applicationContext.getBean("springFactoryBeanProduct", Product.class);
        assertNotNull("Please configure SpringAwareFactory in the ApplicationContext, with id='springFactoryBeanProduct'", product);
        assertEquals("Please configure SpringAwareFactory in the ApplicationContext, with id='springFactoryBeanProduct'", "alwaysTheSame", product.getProductName());
    }

    public void testGettingServiceWithDependencyProducedByFactory() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factories-context.xml");

        ProductDependentService service = applicationContext.getBean("service", ProductDependentService.class);
        assertEquals("Please configure ProductDependentService with id='service' and inject the SpringAwareFactory's product into its constructor", "alwaysTheSame", service.getProduct().getProductName());
    }
}
