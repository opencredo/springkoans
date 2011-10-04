package com.programmingspring.koans.component.scanned.spring;

import org.springframework.stereotype.Component;

@Component
public class LazyComponent {

    private static Boolean INSTANTIATED = false;

    public static boolean isInstantiated() {
        return INSTANTIATED;
    }

    public LazyComponent() {
        INSTANTIATED = true;
    }
}
