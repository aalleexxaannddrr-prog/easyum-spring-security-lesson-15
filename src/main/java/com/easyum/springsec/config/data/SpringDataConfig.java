package com.easyum.springsec.config.data;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(JpaConfig.class)
@EnableJpaRepositories(basePackages = "com.easyum.springsec.repo")
public class SpringDataConfig {

}
