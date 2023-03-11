package com.example.carrental.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String username;

    private String email;

    @Pattern(regexp = "(Owner|Customer)",message = "Role must be in owner or customer only")
    private String role;
    private String password;
    private int Phone_Number;
    private String customerInfo;
    private String carAvailabilty;
    private String bookingRequest;
    private double invoice_Details;

    private LocalDate return_Date;
}
