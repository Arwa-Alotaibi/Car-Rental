package com.example.carrental.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class CarOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private int Phone_Number;
    private String customerInfo;
    private String carAvailabilty;
    private String bookingRequest;
    private double invoice_Details;

    private LocalDate return_Date;
    @OneToOne
//    @MapsId
    @JsonIgnore
    private MyUser myUser;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "carOwner")
    private List<Car> carList;
}
