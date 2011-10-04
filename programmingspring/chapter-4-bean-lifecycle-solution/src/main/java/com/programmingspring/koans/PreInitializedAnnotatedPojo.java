package com.programmingspring.koans;

import javax.annotation.PostConstruct;

public class PreInitializedAnnotatedPojo implements PreInitializedBean {

    private boolean initialized = false;

    @PostConstruct
    public void initialize() {
        initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
}
