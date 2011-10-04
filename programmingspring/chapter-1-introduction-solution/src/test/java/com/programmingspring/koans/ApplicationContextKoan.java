package com.programmingspring.koans;

import junit.framework.TestCase;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ApplicationContextKoan extends TestCase {

    public void testKoan1CreateEmptyApplicationContext() {
        ApplicationContext applicationContext = new GenericApplicationContext();
        
        assertNotNull("ApplicationContext is null." + 
        		" Please create an empty ApplicationContext.", applicationContext);
        assertTrue("ApplicationContext should be of type" +
        		"org.springframework.context.support.GenericApplicationContext", 
        		applicationContext.getClass() == GenericApplicationContext.class);
    }
    
    public void testKoan2CreateARootBeanDefinition() {
    	
    	Class<SampleRepository> beanClass = SampleRepository.class;
    	
    	BeanDefinitionBuilder builder = BeanDefinitionBuilder
    	    .rootBeanDefinition(SampleRepository.class);
    	
    	assertNotNull("BeanDefinitionBuilder is null. Please initialize with a root bean definition.", 
    			      builder);
    	assertTrue("Bean Definition should include the SampleRepository class", 
    			   builder.getBeanDefinition().getBeanClass() == beanClass);
    }
    
    public void testKoan3AddASpringBeanProgrammaticallyToAnApplicationContextRetrievingByType() {
    	GenericApplicationContext applicationContext = new GenericApplicationContext();
    	
    	BeanDefinitionBuilder builderA = BeanDefinitionBuilder
    	    .rootBeanDefinition(SampleRepository.class);
    	
    	applicationContext.registerBeanDefinition("repository", builderA.getBeanDefinition());
    	 
    	Repository beanRetrievedByType = applicationContext.getBean(Repository.class);
    	
    	assertNotNull("Bean retrieved from the ApplicationContext should not be null", beanRetrievedByType);
    }

    public void testKoan4CreateApplicationContextWithValidBeansDocumentAndNoBeans() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("no-beans.xml");
        assertNotNull("ApplicationContext is null. Please create an ApplicationContext with no beans.", applicationContext);
    }

    public void testCreateApplicationContextWithOneBeanRetrievingByType() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("single-bean.xml");

        Repository beanRetrievedByType = applicationContext.getBean(Repository.class);
        assertNotNull("Bean retrieved from the ApplicationContext should not be null", beanRetrievedByType);
    }

    public void testCreateApplicationContextWithOneBeanRetrievingByName() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("single-bean.xml");

        Repository beanRetrievedByName = (Repository) applicationContext.getBean("sampleBean");
        assertNotNull("Bean retrieved from the ApplicationContext should not be null", beanRetrievedByName);
    }

    public void testCreateApplicationContextWithOneBeanRetrievingByNameAndType() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("single-bean.xml");

        Repository beanRetrievedByNameAndType = applicationContext.getBean("sampleBean", Repository.class);
        assertNotNull("Bean retrieved from the ApplicationContext should not be null", beanRetrievedByNameAndType);
    }
}
