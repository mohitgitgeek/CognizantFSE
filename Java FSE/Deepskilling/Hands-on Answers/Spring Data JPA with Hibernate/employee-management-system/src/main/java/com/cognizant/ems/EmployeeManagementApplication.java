package com.cognizant.ems; import org.springframework.boot.*; import org.springframework.boot.autoconfigure.*; import org.springframework.data.jpa.repository.config.*;
@SpringBootApplication @EnableJpaAuditing public class EmployeeManagementApplication {public static void main(String[] a){SpringApplication.run(EmployeeManagementApplication.class,a);}}
