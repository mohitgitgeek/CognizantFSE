package com.cognizant.gateway;
import java.util.Map; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*;
@RestController class FallbackController { @GetMapping("/fallback/{service}") @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE) Map<String,String> fallback(@PathVariable String service) { return Map.of("service",service,"message","Service is temporarily unavailable; please retry."); } }
