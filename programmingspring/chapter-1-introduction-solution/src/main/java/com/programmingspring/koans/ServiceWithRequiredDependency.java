package com.programmingspring.koans;

import org.springframework.beans.factory.annotation.Required;

public class ServiceWithRequiredDependency implements Service {

    private Repository repository;

    public Repository getRepository() {
        return repository;
    }

    @Required
    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
