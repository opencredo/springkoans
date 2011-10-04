package com.programmingspring.koans;

public class PreDestroyedPojo implements PreDestroyedBean {

    private boolean destroyed = false;

    public void destroy() {
        destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }
}
