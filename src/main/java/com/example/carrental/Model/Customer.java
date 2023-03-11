package com.example.carrental.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "the name should be not null!1")
    //  @Column(unique = true)
    private String name;



    @NotNull
    private int phone_number;


    @NotNull(message = "the email should be not null!!")
    @Email(message = "must be a well-formed email address")
    private String email_address;

    @NotNull(message = "the license should be not null!!")
    private String license;


//    @NotNull(message = "the rentdate should be not null!!")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDate rentdate;

//    @NotNull(message = "the returndate should be not null!!")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDate returndate;


    @Positive(message = "the balance should be a positive number!!")
    private double balance;


    @Positive(message = "the age should be a positive number!!")
    @Min(value=20 ,message = "the age should be more than 20")



    private int age;
    @OneToOne
//    @MapsId
    @JsonIgnore
    private MyUser myUser;


    @ManyToMany(mappedBy = "customers")
    private List<Violations> violations_list;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "customer")
    private List<Booking_Order> bookingOrderList;


}
