package com.programmingspring.koans;

import com.programmingspring.koans.component.scanned.spring.LazyComponent;
import com.programmingspring.koans.repository.MyRepository;
import com.programmingspring.koans.service.MyService;
import com.programmingspring.koans.service.scanned.spring.ServiceWithNamedDependency;
import com.programmingspring.koans.service.scanned.spring.ServiceWithTwoConstructorDependencies;
import com.programmingspring.koans.service.scanned.spring.ServiceWithTwoNamedConstructorDependencies;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAnnotationsScanKoan extends TestCase {

    public void testEnabledScanning() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/spring/component-scan.xml");

        assertFalse("Please enable scanning on ServiceWithNamedDependency with @Service annotation", context.getBeansOfType(ServiceWithNamedDependency.class).isEmpty());
    }

    public void testSingletonIsDefault() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/spring/component-scan.xml");

        assertTrue("Please make ServiceWithNamedDependency singleton, removing the @Scope annotation", getService(context, ServiceWithNamedDependency.class) == getService(context, ServiceWithNamedDependency.class));
    }

    public void testNamedDependency() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/spring/component-scan.xml");
        MyService service = getService(context, ServiceWithNamedDependency.class);

        assertNotNull("Please automatically inject an instance of FirstRepository into ServiceWithNamedDependency", service.getRepository());
        assertTrue("Wrong repository", context.getBean("firstRepository") == service.getRepository());
    }

    public void testTwoDependenciesInConstructor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/spring/component-scan.xml");
        ServiceWithTwoConstructorDependencies service = getService(context, ServiceWithTwoConstructorDependencies.class);

        assertTrue("Please inject repositories using @Autowired on the constructor", context.getBean("firstRepository") == service.getRepository());
        assertTrue("Please inject repositories using @Autowired on the constructor", context.getBean("secondRepository") == service.getSecondRepository());
    }

    public void testTwoNamedDependenciesInConstructor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/spring/component-scan.xml");
        ServiceWithTwoNamedConstructorDependencies service = getService(context, ServiceWithTwoNamedConstructorDependencies.class);

        assertTrue("Use @Qualifier to select which repositories to inject into the service (use 'firstRepository' and 'secondRepository')", context.getBean("firstRepository") == service.getRepository());
        assertTrue("Use @Qualifier to select which repositories to inject into the service (use 'firstRepository' and 'secondRepository')", context.getBean("secondRepository") == service.getSecondRepository());
    }

    private <T> T getService(ApplicationContext context, Class<T> serviceClass) {
        assertFalse("Please enable scanning on " + serviceClass.getName() + " with @Service annotation", context.getBeansOfType(serviceClass).isEmpty());
        return context.getBean(serviceClass);
    }

    public void testLazyComponent() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/spring/component-scan.xml");

        assertFalse("Please use the @Lazy annotation to make LazyComponent lazy", LazyComponent.isInstantiated());
        context.getBean(LazyComponent.class);
        assertTrue("The component should definitely be instantiated now", LazyComponent.isInstantiated());
    }

    public void testScanOnlyRepositories() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/spring/component-scan-repositories-only.xml");

        assertTrue("Please make sure repositories are scanned", 0 < context.getBeansOfType(MyRepository.class).size());
        assertEquals("Please make sure only repositories are scanned", 0, context.getBeansOfType(MyService.class).size());
        assertEquals("Please make sure only repositories are scanned", 0, context.getBeansOfType(LazyComponent.class).size());
    }
}
