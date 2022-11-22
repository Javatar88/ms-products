package com.adib.products;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Author Mahmoud Ahmed Said
 */
@SpringBootApplication
public class ProductsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();


    }

}
