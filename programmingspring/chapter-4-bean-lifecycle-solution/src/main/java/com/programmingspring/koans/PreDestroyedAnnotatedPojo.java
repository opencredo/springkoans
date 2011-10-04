package com.programmingspring.koans;

import javax.annotation.PreDestroy;

public class PreDestroyedAnnotatedPojo implements PreDestroyedBean {

    private boolean destroyed = false;

    @PreDestroy
    public void destroy() {
        destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }
}
