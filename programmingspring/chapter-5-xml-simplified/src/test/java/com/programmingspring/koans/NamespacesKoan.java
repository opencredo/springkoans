package com.programmingspring.koans;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NamespacesKoan extends TestCase {

    public void testBeanWithNoNewNamespaces() {
        ApplicationContext context = new ClassPathXmlApplicationContext("c-and-p-namespaces.xml");

        SampleBean firstBean = context.getBean("firstBean", SampleBean.class);
        assertEquals("Please inject 'someValue' into stringDependency using traditional constructor injection", "someValue", firstBean.getStringDependency());
        assertNotNull("Please inject a reference to 'emptyBean' using traditional property injection", firstBean.getBeanDependency());
    }

    public void testBeanWithCNamespace() {
        ApplicationContext context = new ClassPathXmlApplicationContext("c-and-p-namespaces.xml");

        SampleBean secondBean = context.getBean("secondBean", SampleBean.class);
        assertEquals("Please inject 'someValue' into stringDependency using the c namespace", "someValue", secondBean.getStringDependency());
        assertNotNull("Please inject a reference to 'emptyBean' using traditional property injection", secondBean.getBeanDependency());
    }

    public void testBeanWithPNamespace() {
        ApplicationContext context = new ClassPathXmlApplicationContext("c-and-p-namespaces.xml");

        SampleBean thirdBean = context.getBean("thirdBean", SampleBean.class);
        assertEquals("Please inject 'someValue' into stringDependency using traditional constructor injection", "someValue", thirdBean.getStringDependency());
        assertNotNull("Please inject a reference to 'emptyBean' using the p namespace", thirdBean.getBeanDependency());
    }

    public void testBeanWithCAndPNamespaces() {
        ApplicationContext context = new ClassPathXmlApplicationContext("c-and-p-namespaces.xml");

        SampleBean fourthBean = context.getBean("fourthBean", SampleBean.class);
        assertEquals("Please inject 'someValue' into stringDependency using the c namespace", "someValue", fourthBean.getStringDependency());
        assertNotNull("Please inject a reference to 'emptyBean' using the p namespace", fourthBean.getBeanDependency());
    }

    public void testListAsAFirstClassBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-namespace.xml");

        @SuppressWarnings({"unchecked"})
        List<String> listOfStrings = context.getBean("listOfStrings", List.class);

        assertEquals("Please make sure that listOfStrings has 3 elements", 3, listOfStrings.size());
        assertEquals("First element in the list should be 'firstString'", "firstString", listOfStrings.get(0));
        assertEquals("Second element in the list should be 'secondString'", "secondString", listOfStrings.get(1));
        assertEquals("Third element in the list should be 'thirdString'", "thirdString", listOfStrings.get(2));
    }

    public void testListWithNonStringValues() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-namespace.xml");

        List listOfIntegers = context.getBean("listOfIntegers", List.class);

        assertEquals("Please make sure that listOfIntegers has 2 elements", 2, listOfIntegers.size());
        assertTrue("Please make sure that the values in the list are Integers", listOfIntegers.get(0) instanceof Integer);
        assertTrue("Please make sure that the values in the list are Integers", listOfIntegers.get(1) instanceof Integer);
    }

    public void testListInjectedIntoBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-namespace.xml");

        @SuppressWarnings({"unchecked"})
        List<String> listOfStrings = context.getBean("listOfStrings", List.class);
        BeanWithList beanWithLists = context.getBean(BeanWithList.class);

        assertTrue("Please inject a reference to 'listOfStrings' as a dependency to 'beanWithList'", beanWithLists.getListOfStrings() == listOfStrings);
    }

    public void testSpecificListImplementation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-namespace.xml");

        @SuppressWarnings({"unchecked"})
        List<EmptyBean> listOfBeans = context.getBean("listOfBeans", List.class);

        assertTrue("Please make 'listOfBeans' and instance of LinkedList", listOfBeans instanceof LinkedList);
    }

    @SuppressWarnings({"ConstantConditions"})
    public void testListOfBeans() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-namespace.xml");

        @SuppressWarnings({"unchecked"})
        List<EmptyBean> listOfBeans = context.getBean("listOfBeans", List.class);

        assertEquals("Please put 2 references to instances of EmptyBean into the 'listOfBeans': the first one to top-level bean and the second one to an anonymous inner bean",
                2, listOfBeans.size());
        assertTrue("Please make sure all elements in the 'listOfBeans' are beans of type EmptyBean", listOfBeans.get(0) instanceof EmptyBean);
        assertTrue("Please make sure all elements in the 'listOfBeans' are beans of type EmptyBean", listOfBeans.get(1) instanceof EmptyBean);
    }

    public void testListOfBeansWithOneRegularAndOneAnonymous() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-namespace.xml");

        @SuppressWarnings({"unchecked"})
        List<EmptyBean> listOfBeans = context.getBean("listOfBeans", List.class);

        assertEquals("Please make sure only 1 bean of type EmptyBean is declared in the XML as a top-level bean", 1, context.getBeansOfType(EmptyBean.class).size());
        assertTrue("Please make sure the first element of 'listOfBeans' is the top-level bean", context.getBean(EmptyBean.class) == listOfBeans.get(0));
    }

    public void testMapWithTwoEntries() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-namespace.xml");

        Map mapOfBeans = context.getBean("mapOfBeans", Map.class);
        assertEquals("Please make sure 'mapOfBeans' has 2 entries (references to an EmptyBean instance): the first one (key=1) to top-level bean and the second one (key=2) to an anonymous inner bean",
                2, mapOfBeans.size());
    }

    public void testMapWithIntegerKey() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-namespace.xml");

        Map mapOfBeans = context.getBean("mapOfBeans", Map.class);

        assertTrue("Please make sure the keys of the map are interpreted as Integers", mapOfBeans.keySet().iterator().next() instanceof Integer);
    }

    public void testMapOfBeansWithOneRegularAndOneAnonymous() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-namespace.xml");

        @SuppressWarnings({"unchecked"})
        Map<Integer, EmptyBean> mapOfBeans = context.getBean("mapOfBeans", Map.class);

        assertEquals("Please make sure only 1 bean of type EmptyBean is declared in the XML as a top-level bean", 1, context.getBeansOfType(EmptyBean.class).size());
        assertTrue("Please make sure the entry in 'mapOfBeans' under key 1 is the top-level bean", context.getBean(EmptyBean.class) == mapOfBeans.get(1));
    }

    public void testBeanWithProperties() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-namespace.xml");

        BeanWithProperties beanWithProperties = context.getBean("beanWithProperties", BeanWithProperties.class);

        assertNotNull("Please inject an instance of Properties into the 'beanWithProperties'", beanWithProperties.getProperties());
        assertEquals("Please make sure the properties are sourced from sample.properties", "testValue", beanWithProperties.getProperties().getProperty("testKey"));
    }

    @SuppressWarnings({"StringEquality"})
    public void testBeanWithConstant() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-namespace.xml");

        SampleBean beanWithConstant = context.getBean("beanWithConstant", SampleBean.class);
        assertTrue("Please inject Constants.CONSTANT_STRING into the constructor of 'beanWithConstant'", Constants.CONSTANT_STRING == beanWithConstant.getStringDependency());
    }
}
