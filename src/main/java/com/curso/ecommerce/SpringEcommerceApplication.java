package com.curso.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// comando para que no se ejecute el Driver y la conexion a base de datos  MYSQL (exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

public class SpringEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEcommerceApplication.class, args);
	}

}
