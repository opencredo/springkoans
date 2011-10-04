package com.programmingspring.koans;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifecycleKoan extends TestCase {

    public void testPropertyConfiguration() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("property-config.xml");

        ConfigurableBean configurableBean = applicationContext.getBean(ConfigurableBean.class);
        assertEquals("Please configure a PropertyPlaceholderConfigurer to replace properties of ConfigurableBean with values from 'config.properties'", "someValue", configurableBean.getConfiguredString());
    }

    public void testPropertyConfigurationUsingContextNamespace() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("property-config-context-namespace.xml");

        ConfigurableBean configurableBean = applicationContext.getBean(ConfigurableBean.class);
        assertEquals("Please configure a PropertyPlaceholderConfigurer to replace properties of ConfigurableBean with values from 'config.properties'", "someValue", configurableBean.getConfiguredString());
    }

    public void testLazyBeanInstantiation() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("lazy-bean.xml");

        assertFalse("Please make the LazyBean lazy", LazyBean.isInstantiated());
        applicationContext.getBean(LazyBean.class);
        assertTrue("LazyBean should be instantiated after being accessed.", LazyBean.isInstantiated());
    }

    public void testSingletonBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("singleton-vs-prototype.xml");

        SingletonBean firstRetrieved = applicationContext.getBean(SingletonBean.class);
        SingletonBean secondRetrieved = applicationContext.getBean(SingletonBean.class);
        assertTrue("Bean should be singleton by default", firstRetrieved == secondRetrieved);
    }

    public void testPrototypeBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("singleton-vs-prototype.xml");

        PrototypeBean firstRetrieved = applicationContext.getBean(PrototypeBean.class);
        PrototypeBean secondRetrieved = applicationContext.getBean(PrototypeBean.class);
        assertFalse("Please configure PrototypeBean with 'prototype' scope", firstRetrieved == secondRetrieved);
    }

    //todo we should get rid of this right?
    public void testInitializingBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("preinitialized-beans.xml");

        PreInitializedBean preInitializedBean = applicationContext.getBean("frameworkAware", PreInitializedBean.class);
        assertTrue("Please make sure that FrameworkAwarePreInitializedBean implements the InitializingBean interface", preInitializedBean.isInitialized());
    }

    public void testInitMethod() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("preinitialized-beans.xml");

        PreInitializedBean preInitializedBean = applicationContext.getBean("pojo", PreInitializedBean.class);
        assertTrue("Please make sure that PreInitializedPojo is defined with init-method in the Spring config", preInitializedBean.isInitialized());
    }

    public void testPostConstructAnnotation() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("preinitialized-beans.xml");

        PreInitializedBean preInitializedBean = applicationContext.getBean("annotatedPojo", PreInitializedBean.class);
        assertTrue("Please make sure that PreInitializedAnnotatedPojo has the 'initialize' method annotated with @PostConstruct and that '<context:annotation-config/>' is present in the config", preInitializedBean.isInitialized());
    }

    public void testDestroyMethod() {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("predestroyed-beans.xml");

        PreDestroyedBean preDestroyedBean = applicationContext.getBean("pojo", PreDestroyedBean.class);
        assertFalse("Beans shouldn't be destroyed... it's just bean created!", preDestroyedBean.isDestroyed());

        applicationContext.close();

        assertTrue("Please make sure that PreDestroyedPojo is defined with destroy-method in the Spring config", preDestroyedBean.isDestroyed());
    }

    public void testPreDestroyAnnotation() {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("predestroyed-beans.xml");

        PreDestroyedBean preDestroyedBean = applicationContext.getBean("annotatedPojo", PreDestroyedBean.class);
        assertFalse("Beans shouldn't be destroyed... it's just bean created!", preDestroyedBean.isDestroyed());

        applicationContext.close();

        assertTrue("Please make sure that PreDestroyedPojo has the 'destroy' method annotated with @PreDestroy and that '<context:annotation-config/>' is present in the config", preDestroyedBean.isDestroyed());
    }

    public void testSingletonDependingOnPrototype() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("different-scopes.xml");

        SingletonBean firstRetrieved = applicationContext.getBean("standardSingleton", SingletonBean.class);
        SingletonBean secondRetrieved = applicationContext.getBean("standardSingleton", SingletonBean.class);
        assertTrue("The prototype should not behave as a prototype, as it is contained in a singleton", firstRetrieved.getPrototypeBean() == secondRetrieved.getPrototypeBean());
    }

    public void testSingletonDependingOnPrototypeWithLookupMethodInjection() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("different-scopes.xml");

        SingletonBean firstRetrieved = applicationContext.getBean("singletonWithOverriddenLookupMethod", SingletonBean.class);
        SingletonBean secondRetrieved = applicationContext.getBean("singletonWithOverriddenLookupMethod", SingletonBean.class);
        assertTrue("Singleton beans should always the same instance when retrieved", firstRetrieved == secondRetrieved);
        assertFalse("Please configure Spring to override the 'getPrototype' method on 'SingletonBean'", firstRetrieved.getPrototypeBean() == secondRetrieved.getPrototypeBean());
    }
}
