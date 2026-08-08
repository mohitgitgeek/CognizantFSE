package com.cognizant.loan;
import java.math.BigDecimal; import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication; import org.springframework.web.bind.annotation.*;
@SpringBootApplication public class LoanServiceApplication { public static void main(String[] args) { SpringApplication.run(LoanServiceApplication.class,args); } }
@RestController @RequestMapping("/loans") class LoanController { @GetMapping("/{number}") Loan get(@PathVariable String number) { return new Loan(number,"car",new BigDecimal("400000"),new BigDecimal("3258"),18); } }
record Loan(String number,String type,BigDecimal loan,BigDecimal emi,int tenure) {}
