package com.programmingspring.koans;

import junit.framework.TestCase;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlSimplifiedKoan extends TestCase {

    public void testRepeatedPropertyUsingBeanInheritance() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-inheritance.xml");

        SampleBean sampleBean1 = context.getBean("sampleBean1", SampleBean.class);
        SampleBean sampleBean2 = context.getBean("sampleBean2", SampleBean.class);
        SampleBean sampleBean3 = context.getBean("sampleBean3", SampleBean.class);

        EmptyBean emptyBean = context.getBean("emptyBean", EmptyBean.class);

        assertTrue("An instance of EmptyBean named 'emptyBean' must be injected into sampleBean1", sampleBean1.getBeanDependency() == emptyBean);
        assertTrue("An instance of EmptyBean named 'emptyBean' must be injected into sampleBean2", sampleBean2.getBeanDependency() == emptyBean);
        assertTrue("An instance of EmptyBean named 'emptyBean' must be injected into sampleBean3", sampleBean3.getBeanDependency() == emptyBean);
    }

    public void testOverrideConstructorUsingBeanInheritance() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-inheritance.xml");

        SampleBean sampleBean1 = context.getBean("sampleBean1", SampleBean.class);
        SampleBean sampleBean2 = context.getBean("sampleBean2", SampleBean.class);
        SampleBean sampleBean3 = context.getBean("sampleBean3", SampleBean.class);

        assertEquals("Wrong String injected into sampleBean1", "sampleString1", sampleBean1.getStringDependency());
        assertEquals("Wrong String injected into sampleBean2", "sampleString2", sampleBean2.getStringDependency());
        assertEquals("Wrong String injected into sampleBean3", "sampleString3", sampleBean3.getStringDependency());
    }

    public void testAbstractParentForRepeatedPropertyUsingBeanInheritance() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-inheritance.xml");

        try { //can't test with context.containsBean, see https://jira.springsource.org/browse/SPR-8690
            context.getBean("parentSampleBean");
            fail("There should be no instance of parentSampleBean, please declare it abstract");
        } catch (BeanCreationException e) {
            // expected
        }
    }

    public void testInnerBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("inner-beans.xml");

        assertNotNull("Please inject an instance of EmptyBean into sampleBean", context.getBean("sampleBean", SampleBean.class).getBeanDependency());
        assertTrue("Please make the definition of EmptyBean anonymous, so the bean can't be retrieved", context.getBeansOfType(EmptyBean.class).isEmpty());
    }

    public void testImportingXml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("master-config.xml");

        SampleBean sampleBean = context.getBean("sampleBean", SampleBean.class);
        assertNotNull("Please inject 'emptyBean' from imported-config.xml into 'sampleBean' defined in master-config.xml", sampleBean.getBeanDependency());
    }

    public void testBeanWithTwoNames() {
        ApplicationContext context = new ClassPathXmlApplicationContext("id-versus-name.xml");

        SampleBean sampleBean1 = context.getBean("beanName1", SampleBean.class);
        SampleBean sampleBean2 = context.getBean("beanName2", SampleBean.class);

        assertTrue("Please give the same bean two different names, 'beanName1' and 'beanName2'", sampleBean1 == sampleBean2);
    }

    public void testOverridingInheritedReadOnlyBeanDefinition() {
        ApplicationContext context = new ClassPathXmlApplicationContext("overriding-config.xml");

        SampleBean sampleBean = context.getBean("sampleBean", SampleBean.class);
        assertEquals("Please override the 'sampleBean' definition with 'correctString' without modifying 'read-only-config.xml'", "correctString", sampleBean.getStringDependency());
        assertEquals("Please don't modify 'read-only-config.xml'", "wrongString", new ClassPathXmlApplicationContext("read-only-config.xml").getBean(SampleBean.class).getStringDependency());
    }
}
