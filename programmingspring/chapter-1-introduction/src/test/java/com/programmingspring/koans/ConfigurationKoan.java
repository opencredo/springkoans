package com.programmingspring.koans;

import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ConfigurationKoan extends TestCase {

    private ConfigurableBean getConfigurableBeanFromContext() {
        return new ClassPathXmlApplicationContext("configured-bean.xml").getBean(ConfigurableBean.class);
    }

    public void testKoan1InjectingString() {
        assertEquals("Please inject 'someStringValue' into the ConfigurableBean's someString property", "someStringValue", getConfigurableBeanFromContext().getSomeString());
    }

    public void testKoan2InjectingInteger() {
        assertTrue("Please inject 362 into the ConfigurableBean's someInteger property", new Integer(362).equals(getConfigurableBeanFromContext().getSomeInteger()));
        //talk about the fact that this is automatically converted from String using a PropertyEditor
    }

    public void testKoan3InjectingCustomType() {
        WrappedString injectedCustomType = getConfigurableBeanFromContext().getSomeCustomType();
        assertEquals("Please inject 'someWrappedString' to ConfigurableBean's someCustomType property and register the WrappedStringConverter", "someWrappedString", injectedCustomType.getWrappedString());
    }

    @SuppressWarnings("rawtypes")
	public void testKoan4InjectingNonGenericList() {
        List injectedList = getConfigurableBeanFromContext().getNonGenericList();

        assertNotNull("Please inject a list into the ConfigurableBean's nonGenericList property", injectedList);
        assertEquals("Please inject a list with 2 elements into the ConfigurableBean's nonGenericList property", 2, injectedList.size());

        assertTrue("Please inject a list with the first element of type String into the ConfigurableBean's nonGenericList property", injectedList.get(0) instanceof String);
        assertEquals("Please inject a list with the first element of 'someString' into the ConfigurableBean's nonGenericList property", "someString", injectedList.get(0));

        //this is to demonstrate that the non-generified bean can have another bean as its element
        assertTrue("Please define a bean of type WrappedString and inject it as the second element of the ConfigurableBean's nonGenericList property", injectedList.get(1) instanceof WrappedString);
        assertEquals("Please define a bean of type WrappedString, set its wrappedString property to 'anotherWrappedString', and inject it as the second element of the ConfigurableBean's nonGenericList property", "anotherWrappedString", ((WrappedString) injectedList.get(1)).getWrappedString());
    }

    public void testKoan5InjectingGenericList() {
        List<Integer> injectedList = getConfigurableBeanFromContext().getGenericList();

        assertNotNull("Please inject a list of Integers into the ConfigurableBean's genericList property", injectedList);
        assertEquals("Please inject a list with 2 Integers into the ConfigurableBean's nonGenericList property", 2, injectedList.size());

        assertTrue("Please inject a list with the first Integer equal to 123 into the ConfigurableBean's genericList property", injectedList.get(0).equals(123));
        assertTrue("Please inject a list with the second Integer equal to 456 into the ConfigurableBean's genericList property", injectedList.get(1).equals(456));
    }

    public void testKoan6InjectingGenericMap() {
        Map<String, Integer> injectedMap = getConfigurableBeanFromContext().getGenericMap();

        assertNotNull("Please inject a map of String -> Integer into the ConfigurableBean's genericMap property", injectedMap);
        assertTrue("Please inject a map with a 'someKey' key into the ConfigurableBean's genericMap property", injectedMap.containsKey("someKey"));
        assertTrue("Please inject a map with 789 as the value for 'someKey' key into the ConfigurableBean's genericMap property", injectedMap.get("someKey").equals(789));
    }

    public void testKoan7InjectingProperties() {
        Properties injectedProperties = getConfigurableBeanFromContext().getSomeProperties();

        assertNotNull("Please inject properties into the ConfigurableBean's someProperties property", injectedProperties);
        assertTrue("Please inject properties with a 'someKey' key into the ConfigurableBean's someProperties property", injectedProperties.containsKey("someKey"));
        assertEquals("Please inject properties with 'someValue' as the value for 'someKey' key into the ConfigurableBean's someProperties property", "someValue", injectedProperties.getProperty("someKey"));
    }
}
