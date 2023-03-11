package com.example.carrental.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Positive(message = "the price must be positive number")
    private double price;

    @NotNull
    @Pattern(regexp = "(classic|economic|none)",message = "the car class should be classic or economic or none")
    private String car_class;
    private LocalDate available_date;
    private String car_history;
    private String color;


//
//    @ManyToOne
//    @JoinColumn(name = "booking_id" , referencedColumnName = "id")
//    @JsonIgnore
//    private Booking_Order bookingOrder;




    @OneToMany(cascade = CascadeType.ALL ,mappedBy ="car")
    private List<Booking_Order> bookingOrderList;



    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "cars")
    private List<Violations> violations;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "car")
    private List<IsReserved> isReservedList;


    @ManyToOne
    @JoinColumn(name = "carowner_id",referencedColumnName = "id")
    @JsonIgnore
    private CarOwner carOwner;



    @ManyToOne
    @JoinColumn(name = "insurance_id",referencedColumnName = "id")
    @JsonIgnore
    private Insurance insurance;



}
