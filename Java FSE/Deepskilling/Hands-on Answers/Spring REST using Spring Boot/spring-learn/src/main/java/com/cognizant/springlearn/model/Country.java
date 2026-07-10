package com.cognizant.springlearn.model; import jakarta.validation.constraints.*;
public record Country(@NotBlank @Size(min=2,max=2) String code,@NotBlank String name) {}
