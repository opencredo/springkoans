package com.programmingspring.koans;

public class PreInitializedAnnotatedPojo implements PreInitializedBean {

    private boolean initialized = false;

    public void initialize() {
        initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
}
