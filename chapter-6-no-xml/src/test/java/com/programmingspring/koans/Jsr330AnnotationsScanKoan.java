package com.programmingspring.koans;

import com.programmingspring.koans.service.MyService;
import com.programmingspring.koans.service.scanned.jsr330.ServiceWithNamedDependency;
import com.programmingspring.koans.service.scanned.jsr330.ServiceWithTwoConstructorDependencies;
import com.programmingspring.koans.service.scanned.jsr330.ServiceWithTwoNamedConstructorDependencies;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Jsr330AnnotationsScanKoan extends TestCase {

    public void testEnabledScanning() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/jsr330/component-scan.xml");

        assertFalse("Please enable scanning on ServiceWithNamedDependency with @Named annotation", context.getBeansOfType(ServiceWithNamedDependency.class).isEmpty());
    }

    public void testPrototypeIsDefault() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/jsr330/component-scan.xml");

        assertTrue("Please make ServiceWithNamedDependency singleton, using the @Singleton annotation", getService(context, ServiceWithNamedDependency.class) == getService(context, ServiceWithNamedDependency.class));
    }

    public void testNamedDependency() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/jsr330/component-scan.xml");
        MyService service = getService(context, ServiceWithNamedDependency.class);

        assertNotNull("Please automatically inject an instance of FirstRepository into ServiceWithNamedDependency", service.getRepository());
        assertTrue("Wrong repository", context.getBean("firstRepository") == service.getRepository());
    }

    public void testTwoDependenciesInConstructor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/jsr330/component-scan.xml");
        ServiceWithTwoConstructorDependencies service = getService(context, ServiceWithTwoConstructorDependencies.class);

        assertTrue("Please inject repositories using @Inject on the constructor", context.getBean("firstRepository") == service.getRepository());
        assertTrue("Please inject repositories using @Inject on the constructor", context.getBean("secondRepository") == service.getSecondRepository());
    }

    public void testTwoNamedDependenciesInConstructor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/jsr330/component-scan.xml");
        ServiceWithTwoNamedConstructorDependencies service = getService(context, ServiceWithTwoNamedConstructorDependencies.class);

        assertTrue("Use @Named to select which repositories to inject into the service (use 'firstRepository' and 'secondRepository')", context.getBean("firstRepository") == service.getRepository());
        assertTrue("Use @Named to select which repositories to inject into the service (use 'firstRepository' and 'secondRepository')", context.getBean("secondRepository") == service.getSecondRepository());
    }

    private <T> T getService(ApplicationContext context, Class<T> serviceClass) {
        assertFalse("Please enable scanning on " + serviceClass.getName() + " with @Named annotation", context.getBeansOfType(serviceClass).isEmpty());
        return context.getBean(serviceClass);
    }
}
