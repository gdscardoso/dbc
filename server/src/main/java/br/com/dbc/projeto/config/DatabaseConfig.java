package br.com.dbc.projeto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.dbc.projeto.repository")
public class DatabaseConfig {

}
