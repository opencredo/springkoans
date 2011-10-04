package com.programmingspring.koans.config;

import com.programmingspring.koans.repository.MyRepository;
import com.programmingspring.koans.repository.explicit.SampleRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    public MyRepository firstRepository() {
        return new SampleRepository();
    }

}
