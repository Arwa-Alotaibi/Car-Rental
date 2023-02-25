package com.example.graduationproject.Controller;

import com.example.graduationproject.Model.Booking_Order;
import com.example.graduationproject.Service.Booking_OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class Booking_OrderController {
    private Booking_OrderService bookingOrderService;

    public Booking_OrderController(Booking_OrderService bookingOrderService){
        this.bookingOrderService=bookingOrderService;
    }

    @GetMapping("/all")
    public ResponseEntity getall(){
        List<Booking_Order>bookingOrderList=bookingOrderService.Allbooking();
        return ResponseEntity.status(200).body(bookingOrderList);

    }
    @PostMapping("/add")
    public ResponseEntity AddBooking(@Valid @RequestBody Booking_Order bookingOrder){
        bookingOrderService.AddBooking(bookingOrder);
        return ResponseEntity.status(200).body("booking added");
    }

    @PutMapping("/update/{booking_id}")
    public ResponseEntity updatebooking(@Valid @RequestBody Booking_Order bookingOrder , @PathVariable Integer booking_id){
        bookingOrderService.updatebooking(booking_id,bookingOrder);
        return ResponseEntity.status(200).body("booking update");
    }

    @DeleteMapping("/delete/{booking_id}")
    public ResponseEntity delete_booking(@PathVariable Integer booking_id){
        bookingOrderService.Delete_Booking(booking_id);
        return ResponseEntity.status(200).body("booking delete");
    }

    @PutMapping("/assingcar/{booking_id}/{car_id}")
    public ResponseEntity Assing(@PathVariable Integer booking_id ,@PathVariable Integer car_id){
        bookingOrderService.Assign(booking_id,car_id);
        return ResponseEntity.status(200).body("assing");
    }
    @PutMapping("/insurance/car/{booking_id}/{car_id}")
    public ResponseEntity Insurance_calculation_carclass(@PathVariable Integer booking_id ,@PathVariable Integer car_id){
        bookingOrderService.Insurance_calculation_carclass(booking_id,car_id);
        return ResponseEntity.status(200).body("Car insurance has been added successfully :)");
    }

    @PutMapping("/assign/{customer_id}/{booking_id}/{car_id}")
    public ResponseEntity CarRental(@PathVariable Integer customer_id , @PathVariable Integer booking_id,@PathVariable Integer car_id){
        bookingOrderService.Car_rental(customer_id,booking_id,car_id);
        return ResponseEntity.status(200).body("The car has been successfully rented :) ");
    }

    @GetMapping("/cancel/{booking_id}/{customer_id}")
    public ResponseEntity Cancel(@PathVariable Integer booking_id , @PathVariable Integer customer_id){
        bookingOrderService.cancel_reservation(booking_id,customer_id);
        return ResponseEntity.status(200).body("The reservation has been successfully cancelled");
    }
}
