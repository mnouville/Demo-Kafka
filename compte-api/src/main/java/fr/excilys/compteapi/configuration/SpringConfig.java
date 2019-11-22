package fr.excilys.compteapi.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"fr.excilys.compteapi.controller", "fr.excilys.compteapi.model", "fr.excilys.compteapi.repository", "fr.excilys.compteapi.service"})
@PropertySource(value = {"classpath:application.yml"})
@EnableTransactionManagement
public class SpringConfig {

}
