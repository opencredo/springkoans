package com.programmingspring.koans;

import com.programmingspring.koans.component.explicit.spring.ComponentWithOptionalDependency;
import com.programmingspring.koans.service.MyService;
import com.programmingspring.koans.service.explicit.spring.*;
import junit.framework.TestCase;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAnnotationsNoScanKoan extends TestCase {

    public void testFieldAutowiring() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/spring/services-property-and-field-dependencies.xml");

        MyService service = context.getBean(ServiceWithFieldDependency.class);

        assertNotNull("Please inject a repository into the service using @Autowired", service.getRepository());
    }

    public void testPropertyAutowiring() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/spring/services-property-and-field-dependencies.xml");

        ServiceWithPropertyDependency service = context.getBean(ServiceWithPropertyDependency.class);

        assertNotNull("Please inject a repository into the service using @Autowired", service.getRepository());
        assertTrue("Please inject a repository using the setter (not field)", service.wasSetterCalled());
    }

    public void testConstructorAutowiring() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/spring/service-constructor-dependency.xml");

        MyService service = context.getBean(ServiceWithConstructorDependency.class);

        assertNotNull("Please inject a repository using @Autowired on the constructor", service.getRepository());
    }

    public void testNamedDependency() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/spring/service-named-property-dependency.xml");

        MyService service = context.getBean(ServiceWithNamedDependency.class);

        assertNotNull("Use @Qualifier to select which repository to inject into the service (use 'firstRepository')", service.getRepository());
        assertTrue("Please inject firstRepository into the service", context.getBean("firstRepository") == service.getRepository());
    }

    public void testTwoDependenciesInConstructor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/spring/service-two-constructor-dependencies.xml");

        ServiceWithTwoConstructorDependencies service = context.getBean(ServiceWithTwoConstructorDependencies.class);

        assertNotNull("Please inject repositories using @Autowired on the constructor", service.getRepository());
        assertNotNull("Please inject repositories using @Autowired on the constructor", service.getSecondRepository());
        assertTrue("Wrong first repository", context.getBean("firstRepository") == service.getRepository());
        assertTrue("Wrong second repository", context.getBean("secondRepository") == service.getSecondRepository());
    }

    public void testTwoNamedDependenciesInConstructor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/spring/service-two-named-constructor-dependencies.xml");

        ServiceWithTwoNamedConstructorDependencies service = context.getBean(ServiceWithTwoNamedConstructorDependencies.class);

        assertNotNull("Use @Qualifier to select which repositories to inject into the service (use 'firstRepository' and 'secondRepository')", service.getRepository());
        assertNotNull("Use @Qualifier to select which repositories to inject into the service (use 'firstRepository' and 'secondRepository')", service.getSecondRepository());
        assertTrue("Wrong first repository", context.getBean("firstRepository") == service.getRepository());
        assertTrue("Wrong second repository", context.getBean("secondRepository") == service.getSecondRepository());
    }

    public void testUnsatisfiedOptionalDependency() {
        ApplicationContext context = null;
        try {
            context = new ClassPathXmlApplicationContext("explicit/spring/component-with-optional-dependency.xml");
        } catch (BeansException e) {
            fail("Please mark the dependency of ComponentWithOptionalDependency optional");
        }

        ComponentWithOptionalDependency component = context.getBean(ComponentWithOptionalDependency.class);
        assertNull("Unsatisfied optional dependency should be null", component.getDependency());
    }

    public void testSatisfiedOptionalDependency() {
        ApplicationContext context = new ClassPathXmlApplicationContext("explicit/spring/component-with-optional-dependency.xml", "explicit/spring/optional-dependency.xml");

        ComponentWithOptionalDependency component = context.getBean(ComponentWithOptionalDependency.class);

        assertNotNull("Please declare an instance of OptionalDependency in 'explicit/spring/optional-dependency.xml'", component.getDependency());
    }
}
