package com.programmingspring.koans;

import junit.framework.TestCase;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectionKoan extends TestCase {

    public void testCreateServiceUsingConstructorInjection() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dependency-injection.xml");

        Service service = applicationContext.getBean(ServiceWithFinalDependency.class);
        assertNotNull("The service has been constructed with a Null repository. Please inject a repository instance", service.getRepository());
    }

    public void testCreateServiceUsingPropertyInjection() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dependency-injection.xml");

        Service service = applicationContext.getBean(ServiceWithDependency.class);
        assertNotNull("The service has been constructed without a repository. Please inject a repository instance", service.getRepository());
    }

    public void testCreateServiceWithDefaultDependency() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dependency-injection.xml");

        Service service = applicationContext.getBean(ServiceWithDefaultDependency.class);
        assertTrue("The service has been constructed with a wrong dependency (probably the default one). Please inject an instance of SampleRepository", service.getRepository() instanceof SampleRepository);
    }

    public void testContextCreationFailsWhenRequiredDependencyIsNotProvided() {
        try {
            new ClassPathXmlApplicationContext("dependency-injection-required.xml");
            fail("ApplicationContext containing unsatisfied required dependencies should not be successfully created. Please mark a dependency in ServiceWithRequiredDependency with @Required.");
        }
        catch (BeanCreationException e) {
            //success
        }
    }

    public void testCreateServiceUsingConstructorInjectionWithCNamespace() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dependency-injection-namespaces.xml");

        Service service = applicationContext.getBean(ServiceWithFinalDependency.class);
        assertNotNull("The service has been constructed with a Null repository. Please inject a repository instance (using the c namespace)", service.getRepository());
    }

    public void testCreateServiceUsingPropertyInjectionWithPNamespace() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dependency-injection-namespaces.xml");

        Service service = applicationContext.getBean(ServiceWithDependency.class);
        assertNotNull("The service has been constructed without a repository. Please inject a repository instance (using the p namespace)", service.getRepository());
    }

    public void testCreateServiceWithDefaultDependencyWithPNamespace() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dependency-injection-namespaces.xml");

        Service service = applicationContext.getBean(ServiceWithDefaultDependency.class);
        assertTrue("The service has been constructed with a wrong dependency (probably the default one). Please inject an instance of SampleRepository (using the p namespace)", service.getRepository() instanceof SampleRepository);
    }
}
