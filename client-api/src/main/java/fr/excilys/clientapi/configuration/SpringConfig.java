package fr.excilys.clientapi.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"controller", "model", "repository", "service"})
@PropertySource(value = {"classpath:application.properties"})
@EnableWebMvc
@EnableTransactionManagement
public class SpringConfig {

}
