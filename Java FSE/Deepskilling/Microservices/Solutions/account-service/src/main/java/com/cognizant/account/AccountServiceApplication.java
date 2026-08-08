package com.cognizant.account;
import java.math.BigDecimal; import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication; import org.springframework.web.bind.annotation.*;
@SpringBootApplication public class AccountServiceApplication { public static void main(String[] args) { SpringApplication.run(AccountServiceApplication.class,args); } }
@RestController @RequestMapping("/accounts") class AccountController { @GetMapping("/{number}") Account get(@PathVariable String number) { return new Account(number,"savings",new BigDecimal("234343")); } }
record Account(String number,String type,BigDecimal balance) {}
