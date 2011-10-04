package com.programmingspring.koans;

import org.springframework.beans.factory.InitializingBean;

public class FrameworkAwarePreInitializedBean implements PreInitializedBean, InitializingBean {

    private boolean initialized = false;

    @Override
    public void afterPropertiesSet() throws Exception {
        initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
}
