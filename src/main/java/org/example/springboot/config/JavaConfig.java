package org.example.springboot.config;

import org.example.springboot.DevProfile;
import org.example.springboot.ProductionProfile;
import org.example.springboot.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @ConditionalOnProperty
            (name = "netology.profile.dev", havingValue = "true")
    @Bean
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @ConditionalOnProperty
            (name = "netology.profile.prod", havingValue = "true")
    @Bean
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
