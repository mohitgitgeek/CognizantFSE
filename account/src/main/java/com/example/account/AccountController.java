package com.example.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/accounts/{number}")
    public AccountResponse getAccount(@PathVariable String number) {
        return new AccountResponse(number, "savings", 234343.0);
    }

    public record AccountResponse(String number, String type, double balance) {}
}
