package com.programmingspring.koans;

public class SampleBean {

    private final String stringDependency;
    private EmptyBean beanDependency;

    public SampleBean(String stringDependency) {
        this.stringDependency = stringDependency;
    }

    public String getStringDependency() {
        return stringDependency;
    }

    public EmptyBean getBeanDependency() {
        return beanDependency;
    }

    public void setBeanDependency(EmptyBean beanDependency) {
        this.beanDependency = beanDependency;
    }
}
