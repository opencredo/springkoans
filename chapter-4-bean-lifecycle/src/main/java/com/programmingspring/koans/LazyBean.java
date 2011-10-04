package com.programmingspring.koans;

public class LazyBean {

    private static Boolean INSTANTIATED = false;

    public static boolean isInstantiated() {
        return INSTANTIATED;
    }

    public LazyBean() {
        INSTANTIATED = true;
    }
}
