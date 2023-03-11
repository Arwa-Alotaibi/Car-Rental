package com.example.carrental.Controller;

import com.example.carrental.Model.Booking_Order;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Service.Booking_OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class Booking_OrderController {
    private Booking_OrderService bookingOrderService;

    public Booking_OrderController(Booking_OrderService bookingOrderService){
        this.bookingOrderService=bookingOrderService;
    }

    // all admin owner customer can see booking
    @GetMapping("/all")
    public ResponseEntity getall(){
        List<Booking_Order>bookingOrderList=bookingOrderService.Allbooking();
        return ResponseEntity.status(200).body(bookingOrderList);

    }
    // all admin owner customer can see booking
    @PostMapping("/add")
    public ResponseEntity AddBooking(@Valid @RequestBody Booking_Order bookingOrder){
        bookingOrderService.AddBooking(bookingOrder);
        return ResponseEntity.status(200).body("booking added");
    }

    @PutMapping("/update/{booking_id}")
    public ResponseEntity updatebooking(@Valid @RequestBody Booking_Order bookingOrder , @PathVariable Integer booking_id,@AuthenticationPrincipal MyUser user){
        bookingOrderService.updatebooking(booking_id,bookingOrder,user);
        return ResponseEntity.status(200).body("booking update");
    }

    //
    @DeleteMapping("/delete/{booking_id}")
    public ResponseEntity delete_booking(@PathVariable Integer booking_id,@AuthenticationPrincipal MyUser user){
        bookingOrderService.Delete_Booking(booking_id,user);
        return ResponseEntity.status(200).body("booking delete");
    }

    //admin
    @PutMapping("/assingcar/{booking_id}/{car_id}")
    public ResponseEntity Assing(@PathVariable Integer booking_id ,@PathVariable Integer car_id){
        bookingOrderService.Assign(booking_id,car_id);
        return ResponseEntity.status(200).body("assing");
    }
    //Admin
    @PutMapping("/insurance/car/{booking_id}/{car_id}")
    public ResponseEntity Insurance_calculation_carclass(@PathVariable Integer booking_id ,@PathVariable Integer car_id){
        bookingOrderService.Insurance_calculation_carclass(booking_id,car_id);
        return ResponseEntity.status(200).body("Car insurance has been added successfully :)");
    }

    @PutMapping("/rent/{booking_id}/{car_id}")
    public ResponseEntity CarRental( @PathVariable Integer booking_id,@PathVariable Integer car_id, @AuthenticationPrincipal MyUser user){
        bookingOrderService.Car_rental( user.getId(), booking_id,car_id);
        return ResponseEntity.status(200).body("The car has been successfully rented :) ");
    }

    @PutMapping ("/cancel/{booking_id}")
    public ResponseEntity Cancel(@PathVariable Integer booking_id , @AuthenticationPrincipal MyUser user){
        bookingOrderService.cancel_reservation(booking_id,user.getId());
        return ResponseEntity.status(200).body("The reservation has been successfully cancelled");
    }

    @GetMapping("/check/{booking_id}/{car_id}/{reservation_date}")

    public ResponseEntity check_date(@PathVariable Integer booking_id, @PathVariable Integer car_id, @PathVariable LocalDate reservation_date, @AuthenticationPrincipal MyUser user){
        bookingOrderService.IsReserved(user.getId(),booking_id,car_id,reservation_date);
        return ResponseEntity.status(200).body("The reservation has been successfully checked for availability");


    }
    @GetMapping("/payment/{Customer_Choice}/{period}/{booking_id}")
    public ResponseEntity payment(@PathVariable String customer_choice,@PathVariable int period,@PathVariable Integer booking_id ,@AuthenticationPrincipal MyUser user_id) {
        bookingOrderService.Payment_method(customer_choice,period,booking_id,user_id.getId());
        return ResponseEntity.status(200).body("payment method has been successfully checked");

    }

//
    @GetMapping("/black_list/{booking_id}/{car_id}")
    public ResponseEntity Black_list( @PathVariable Integer booking_id,@PathVariable Integer car_id,@AuthenticationPrincipal MyUser user_id){
        bookingOrderService.black_list(user_id.getId(),booking_id,car_id);
        return ResponseEntity.status(200).body("black list has beet check");
    }
}
