package com.programmingspring.koans;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ConfigurableBean {

    private String someString;
    private Integer someInteger;
    private WrappedString someCustomType;

    @SuppressWarnings("rawtypes")
	private List nonGenericList;
    private List<Integer> genericList;
    private Map<String, Integer> genericMap;

    private Properties someProperties;

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public Integer getSomeInteger() {
        return someInteger;
    }

    public void setSomeInteger(Integer someInteger) {
        this.someInteger = someInteger;
    }

    public WrappedString getSomeCustomType() {
        return someCustomType;
    }

    public void setSomeCustomType(WrappedString someCustomType) {
        this.someCustomType = someCustomType;
    }

    @SuppressWarnings("rawtypes")
	public List getNonGenericList() {
        return nonGenericList;
    }

    @SuppressWarnings("rawtypes")
	public void setNonGenericList(List nonGenericList) {
        this.nonGenericList = nonGenericList;
    }

    public List<Integer> getGenericList() {
        return genericList;
    }

    public void setGenericList(List<Integer> genericList) {
        this.genericList = genericList;
    }

    public Map<String, Integer> getGenericMap() {
        return genericMap;
    }

    public void setGenericMap(Map<String, Integer> genericMap) {
        this.genericMap = genericMap;
    }

    public Properties getSomeProperties() {
        return someProperties;
    }

    public void setSomeProperties(Properties someProperties) {
        this.someProperties = someProperties;
    }
}
