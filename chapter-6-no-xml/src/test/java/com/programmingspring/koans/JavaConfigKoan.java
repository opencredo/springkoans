package com.programmingspring.koans;

import com.programmingspring.koans.config.AppConfig;
import com.programmingspring.koans.config.RepositoryConfig;
import com.programmingspring.koans.config.ServicesConfig;
import com.programmingspring.koans.repository.explicit.SampleRepository;
import com.programmingspring.koans.service.MyService;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigKoan extends TestCase {

    public void testExplicitJavaConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class);

        assertTrue("Please annotate the method with @Bean in RepositoryConfig.java", context.containsBean("firstRepository"));
        assertTrue("Please make sure 'firstRepository' is an instance of SampleRepository", context.getBean("firstRepository") instanceof SampleRepository);
    }

    public void testAutowiringOfAnotherConfigClass() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class, ServicesConfig.class);

        ServicesConfig servicesConfig = context.getBean(ServicesConfig.class);

        assertNotNull("Please autowire RepositoryConfig into ServicesConfig", servicesConfig.getRepositoryConfig());
    }

    public void testAutowiringOfAnotherBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class, ServicesConfig.class);

        ServicesConfig servicesConfig = context.getBean(ServicesConfig.class);

        assertNotNull("Please autowire an instance of MyRepository into ServicesConfig", servicesConfig.getRepository());
        assertTrue("Wrong instance of repository injected, it should be a Spring-managed singleton bean", servicesConfig.getRepository() == context.getBean("firstRepository"));
    }

    public void testDependencyInjectionAutowiredRepository() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class, ServicesConfig.class);

        MyService service = context.getBean("someService", MyService.class);

        assertNotNull("Please inject the autowired instance of SampleRepository into 'someService'", service.getRepository());
        assertTrue("Wrong instance of repository injected, it should be a Spring-managed singleton bean", service.getRepository() == context.getBean("firstRepository"));
    }

    public void testDependencyInjectionAutowiredRepositoryConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class, ServicesConfig.class);

        MyService service = context.getBean("someOtherService", MyService.class);

        assertNotNull("Please inject an instance of SampleRepository into 'someOtherService' (get it from the autowired RepositoryConfig)", service.getRepository());
        assertTrue("Wrong instance of repository injected, it should be a Spring-managed singleton bean", service.getRepository() == context.getBean("firstRepository"));
    }

    public void testDependencyInjectionImportedConfigClasses() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        assertTrue("Please import RepositoryConfig and ServicesConfig into AppConfig", context.containsBean("someService"));

        MyService service = context.getBean("someService", MyService.class);

        assertNotNull("Repository should not be null if all previous tests have passed", service.getRepository());
        assertTrue("Repository does not seem to be a singleton, are all config classes annotated with @Configuration?", service.getRepository() == context.getBean("firstRepository"));
    }

    public void testScannedConfigBeans() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/spring/configuration-scan.xml");

        assertTrue("Please enable scanning for package 'com.programmingspring.koans.config' in 'configuration-scan.xml'", context.containsBean("someService"));

        MyService service = context.getBean("someService", MyService.class);

        assertNotNull("Repository should not be null if all previous tests have passed", service.getRepository());
        assertTrue("Repository does not seem to be a singleton, are all config classes annotated with @Configuration?", service.getRepository() == context.getBean("firstRepository"));
    }

    public void testExplicitConfigBeanDefinition() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanned/spring/configuration-explicit.xml");

        assertTrue("Please declare an instance of AppConfig explicitly in 'configuration-explicit.xml'", context.containsBean("someService"));

        MyService service = context.getBean("someService", MyService.class);

        assertNotNull("Repository should not be null if all previous tests have passed", service.getRepository());
        assertTrue("Repository does not seem to be a singleton, are all config classes annotated with @Configuration?", service.getRepository() == context.getBean("firstRepository"));
    }
}
