package com.example.carrental.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


//    private String duration_rent;
//    @NotNull
//    private Date start_date;
//    @NotNull
//    private Date end_date;
//    @Pattern(regexp = "(ADMIN|USER)",message = "Role must be in admin or user only")

    @NotNull(message = "the insurance type should be not null!! ")
    @Pattern(regexp = "(Third party insurance|full insurance|none)",message = "insurance_type must be Third party insurance or full insurance or none")
    private String insurance_type;

    @NotNull
    private double total_days;
    private double total_price;

    private double insurance_price;

    @ManyToOne
    @JoinColumn(name = "customer_id" , referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

//
//    @OneToMany(cascade = CascadeType.ALL ,mappedBy ="bookingOrder")
//    private List<Car> carList;


    @ManyToOne
    @JoinColumn(name = "car_id" , referencedColumnName = "id")
    @JsonIgnore
    private Car car;




}
