package com.programmingspring.koans;

import com.programmingspring.koans.service.MyService;
import com.programmingspring.koans.service.explicit.jsr330.*;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Jsr330AnnotationsNoScanKoan extends TestCase {

    public void testFieldAutowiring() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/jsr330/services-property-and-field-dependencies.xml");

        MyService service = context.getBean(ServiceWithFieldDependency.class);

        assertNotNull("Please inject a repository into the service using @Inject", service.getRepository());
    }

    public void testPropertyAutowiring() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/jsr330/services-property-and-field-dependencies.xml");

        ServiceWithPropertyDependency service = context.getBean(ServiceWithPropertyDependency.class);

        assertNotNull("Please inject a repository into the service using @Inject", service.getRepository());
        assertTrue("Please inject a repository using the setter (not field)", service.wasSetterCalled());
    }

    public void testConstructorAutowiring() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/jsr330/service-constructor-dependency.xml");

        MyService service = context.getBean(ServiceWithConstructorDependency.class);

        assertNotNull("Please inject a repository using @Inject on the constructor", service.getRepository());
    }

    public void testNamedDependency() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/jsr330/service-named-property-dependency.xml");

        MyService service = context.getBean(ServiceWithNamedDependency.class);

        assertNotNull("Use @Named to select which repository to inject into the service (use 'firstRepository')", service.getRepository());
        assertTrue("Please inject firstRepository into the service", context.getBean("firstRepository") == service.getRepository());
    }

    public void testTwoDependenciesInConstructor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/jsr330/service-two-constructor-dependencies.xml");

        ServiceWithTwoConstructorDependencies service = context.getBean(ServiceWithTwoConstructorDependencies.class);

        assertNotNull("Please inject repositories using @Inject on the constructor", service.getRepository());
        assertNotNull("Please inject repositories using @Inject on the constructor", service.getSecondRepository());
        assertTrue("Wrong first repository", context.getBean("firstRepository") == service.getRepository());
        assertTrue("Wrong second repository", context.getBean("secondRepository") == service.getSecondRepository());
    }

    public void testTwoNamedDependenciesInConstructor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/jsr330/service-two-named-constructor-dependencies.xml");

        ServiceWithTwoNamedConstructorDependencies service = context.getBean(ServiceWithTwoNamedConstructorDependencies.class);

        assertNotNull("Use @Named to select which repositories to inject into the service (use 'firstRepository' and 'secondRepository')", service.getRepository());
        assertNotNull("Use @Named to select which repositories to inject into the service (use 'firstRepository' and 'secondRepository')", service.getSecondRepository());
        assertTrue("Wrong first repository", context.getBean("firstRepository") == service.getRepository());
        assertTrue("Wrong second repository", context.getBean("secondRepository") == service.getSecondRepository());
    }

}
