package com.programmingspring.koans.component.scanned.spring;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyComponent {

    private static Boolean INSTANTIATED = false;

    public static boolean isInstantiated() {
        return INSTANTIATED;
    }

    public LazyComponent() {
        INSTANTIATED = true;
    }
}
