package com.cognizant.springlearn.model; import jakarta.validation.constraints.*; import java.time.*;
public record Employee(long id,@NotBlank String name,@NotNull @Min(1) Double salary,@Past LocalDate dateOfBirth,String department) {}
