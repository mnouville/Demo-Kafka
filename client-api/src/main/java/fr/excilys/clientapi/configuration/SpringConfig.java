package fr.excilys.clientapi.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"fr.excilys.clientapi.controller", "fr.excilys.clientapi.model", "fr.excilys.clientapi.repository", "fr.excilys.clientapi.service"})
@PropertySource(value = {"classpath:application.properties"})
@EnableTransactionManagement
public class SpringConfig {

}
