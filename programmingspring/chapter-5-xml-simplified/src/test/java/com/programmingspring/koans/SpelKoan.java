package com.programmingspring.koans;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;

public class SpelKoan extends TestCase {

    public void testSystemProperties() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spel-config.xml");

        SpelBean spelBean = context.getBean(SpelBean.class);
        assertEquals("Please inject the current user's name into the constructor of 'spelBean'", System.getProperty("user.name"), spelBean.getUsername());
    }

    public void testReferToAnotherBeansProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spel-config.xml");

        String sampleBeansDependency = context.getBean(SampleBean.class).getStringDependency();
        SpelBean spelBean = context.getBean(SpelBean.class);
        assertTrue("Please inject sampleBean's stringDependency into spelBean's stringFromAnotherBean dependency", sampleBeansDependency == spelBean.getStringFromAnotherBean());
    }

    public void testValueAnnotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spel-config.xml");

        SpelBean spelBean = context.getBean(SpelBean.class);
        assertNotNull("Please make sure that @Value annotations are processed at startup", spelBean.wasConstructedOnFriday());
        assertEquals("Wrong value injected into 'constructedOnFriday' property of 'spelBean'", isFriday(), spelBean.wasConstructedOnFriday().booleanValue());
    }

    private boolean isFriday() {
        return Calendar.FRIDAY == Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
}
