package com.loeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author LT(286269159 @ qq.com)
 */
@ComponentScan("com.loeo")
@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
