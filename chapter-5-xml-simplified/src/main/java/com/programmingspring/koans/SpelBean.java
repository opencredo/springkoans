package com.programmingspring.koans;

import org.springframework.beans.factory.annotation.Value;

public class SpelBean {

    private final String username;
    private String stringFromAnotherBean;
    private Boolean constructedOnFriday;

    public SpelBean(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getStringFromAnotherBean() {
        return stringFromAnotherBean;
    }

    public void setStringFromAnotherBean(String stringFromAnotherBean) {
        this.stringFromAnotherBean = stringFromAnotherBean;
    }

    public Boolean wasConstructedOnFriday() {
        return constructedOnFriday;
    }

    @Value("#{T(java.util.Calendar).getInstance().get(T(java.util.Calendar).DAY_OF_WEEK) == T(java.util.Calendar).FRIDAY}")
    public void setConstructedOnFriday(Boolean constructedOnFriday) {
        this.constructedOnFriday = constructedOnFriday;
    }
}
