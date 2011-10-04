package com.programmingspring.koans;

public class FrameworkAwarePreInitializedBean implements PreInitializedBean {

    private boolean initialized = false;

    public void afterPropertiesSet() throws Exception {
        initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
}
