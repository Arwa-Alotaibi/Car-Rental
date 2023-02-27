package com.example.graduationproject.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date rentdate;

    @NotNull(message = "the returndate should be not null!!")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date returndate;


    @Positive(message = "the balance should be a positive number!!")
    private double balance;


    @Positive(message = "the age should be a positive number!!")
    private int age;



}
