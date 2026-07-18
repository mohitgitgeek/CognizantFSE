package com.example.composite;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CompositeController {

    private final RestTemplate restTemplate;

    public CompositeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/composite/{number}")
    public CompositeResponse getComposite(@PathVariable String number) {
        AccountResponse account = restTemplate.getForObject("http://localhost:8081/accounts/" + number, AccountResponse.class);
        LoanResponse loan = restTemplate.getForObject("http://localhost:8082/loans/" + number, LoanResponse.class);

        return new CompositeResponse(number, account != null ? account.type() : "unknown",
                account != null ? account.balance() : 0.0,
                loan != null ? loan.type() : "unknown",
                loan != null ? loan.loan() : 0.0,
                loan != null ? loan.emi() : 0.0,
                loan != null ? loan.tenure() : 0);
    }

    public record CompositeResponse(String number, String accountType, double balance, String loanType,
                                    double loanAmount, double emi, int tenure) {}
    public record AccountResponse(String number, String type, double balance) {}
    public record LoanResponse(String number, String type, double loan, double emi, int tenure) {}
}
