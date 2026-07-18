package com.example.loan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/loans/{number}")
    public LoanResponse getLoan(@PathVariable String number) {
        return new LoanResponse(number, "car", 400000.0, 3258.0, 18);
    }

    public record LoanResponse(String number, String type, double loan, double emi, int tenure) {}
}
