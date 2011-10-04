package com.programmingspring.koans.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RepositoryConfig.class, ServicesConfig.class})
public class AppConfig {
}
