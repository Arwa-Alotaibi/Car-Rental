package com.example.carrental.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "the name should be not null!1")
    //  @Column(unique = true)
    private String name;

    private String username;


    @Pattern(regexp = "(Owner|Customer)",message = "Role must be in owner or customer only")
    private String role;

    @NotNull
    private int phone_number;

    private String password;

    @NotNull(message = "the email should be not null!!")
    @Email(message = "must be a well-formed email address")
    private String email_address;

    @NotNull(message = "the license should be not null!!")
    private String license;


    @NotNull(message = "the rentdate should be not null!!")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDate rentdate;

    @NotNull(message = "the returndate should be not null!!")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDate returndate;


    @Positive(message = "the balance should be a positive number!!")
    private double balance;


    @Positive(message = "the age should be a positive number!!")
    @Min(value=20 ,message = "the age should be more than 20")

    private int age;



}
