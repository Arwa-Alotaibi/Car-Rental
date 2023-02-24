package com.example.graduationproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String duration_rent;
    @NotNull
    private Date start_date;
    @NotNull
    private Date end_date;

    @Positive
    private double total_price;

    @ManyToOne
    @JoinColumn(name = "customer_id" , referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;


    @OneToMany(cascade = CascadeType.ALL ,mappedBy ="bookingOrder")
    private List<Car> carList;
}
