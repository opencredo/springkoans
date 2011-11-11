package com.programmingspring.koans;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProAppContextKoan extends TestCase {

    public void testKoan1CreatingContextFromMultipleXMLFiles() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        		new String[]{"multi-file-context-service.xml", 
			     			 "multi-file-context-repository.xml"});

        Service service = applicationContext.getBean(ServiceWithDependency.class);
        assertNotNull("Please import the 'multi-file-context-service.xml' into the 'multi-file-context-master.xml'", service);
        assertNotNull("Please import the 'multi-file-context-repository.xml' into the 'multi-file-context-master.xml'", service.getRepository());
    }

    public void testKoan2UsingASpecificProfile() {
        ConfigurableApplicationContext applicationContext = 
        		new ClassPathXmlApplicationContext(new String[]{"profile-context.xml"}, false);
        applicationContext.getEnvironment().setActiveProfiles("test");
        applicationContext.refresh();


        Service service = applicationContext.getBean(ServiceWithDependency.class);
        assertNotNull("Please define a 'test' profile with an instance of TestRepository injected into the service", 
        		service.getRepository());
        assertTrue("Please define a 'test' profile with an instance of TestRepository injected into the service", 
        		service.getRepository() instanceof TestRepository);
    }
    
    public void testOptionalProductionProfile() {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"profile-context.xml"}, false);
        applicationContext.getEnvironment().setActiveProfiles("production");
        applicationContext.refresh();


        Service service = applicationContext.getBean(ServiceWithDependency.class);
        assertNotNull("Please define a 'production' profile with an instance of ProductionRepository injected into the service", service.getRepository());
        assertTrue("Please define a 'production' profile with an instance of ProductionRepository injected into the service", service.getRepository() instanceof ProductionRepository);
    }
    
}
