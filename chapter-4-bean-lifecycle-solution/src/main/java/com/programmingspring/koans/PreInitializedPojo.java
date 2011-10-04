package com.programmingspring.koans;

public class PreInitializedPojo implements PreInitializedBean {

    private boolean initialized = false;

    public void initialize() {
        initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
}
