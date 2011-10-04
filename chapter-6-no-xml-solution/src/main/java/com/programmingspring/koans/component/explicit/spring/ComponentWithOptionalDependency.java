package com.programmingspring.koans.component.explicit.spring;

import org.springframework.beans.factory.annotation.Autowired;

public class ComponentWithOptionalDependency {

    @Autowired(required = false)
    private OptionalDependency dependency;

    public OptionalDependency getDependency() {
        return dependency;
    }
}
