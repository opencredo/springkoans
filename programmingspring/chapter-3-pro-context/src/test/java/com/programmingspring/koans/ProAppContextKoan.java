package com.programmingspring.koans;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProAppContextKoan extends TestCase {

    @SuppressWarnings("null")
	public void testKoan1CreatingContextFromMultipleXMLFiles() {
    	// Create a ClassPathXmlApplicationContext from multiple XML configuration files that 
    	// together will build a valid bean that is an instance of the ServiceWithDependency class
        ApplicationContext applicationContext = null;

        Service service = applicationContext.getBean(ServiceWithDependency.class);
        assertNotNull("Please ensure that the appliction context has the 'multi-file-context-service.xml'", service);
        assertNotNull("Please ensure that the appliction context has 'multi-file-context-repository.xml'", service.getRepository());
    }

    public void testKoan2UsingASpecificProfile() {
    	// Instruct the ApplicationContext not to initialize the beans just yet.
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"profile-context.xml"});
        // Specify that you're using the 'test' environment
        
        // Refresh the application context so that only the right beans for the profile are active.
        

        Service service = applicationContext.getBean(ServiceWithDependency.class);
        assertNotNull("Please define a 'test' profile with an instance of TestRepository injected into the service", service.getRepository());
        assertTrue("Please define a 'test' profile with an instance of TestRepository injected into the service", service.getRepository() instanceof TestRepository);
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
