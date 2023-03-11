package com.example.carrental.Controller;

import com.example.carrental.Model.Car;
import com.example.carrental.Model.Customer;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    //Get All
    @GetMapping("/all/customers")
    public ResponseEntity GetAllCustomer(){
        List<Customer> customerList = customerService.GetAll();
        return ResponseEntity.status(200).body(customerList);
    }
    //Add Customer
    @PostMapping("/add")
    public ResponseEntity AddCustomer(@Valid @RequestBody Customer customer){
        customerService.AddCustomer(customer);
        return ResponseEntity.status(200).body("customer added!");
    }
    //update customer
    @PutMapping("/update/{customer_id}")
    public ResponseEntity UpdateCustomer( @Valid @RequestBody Customer customer,@PathVariable Integer customer_id){
        customerService.UpdateCustomer(customer_id,customer);
        return ResponseEntity.status(200).body("customer updated!");
    }
    // delete customer
    @DeleteMapping("/delete/{customer_id}")
    public ResponseEntity DeleteCustomer(@PathVariable Integer customer_id){
        customerService.DeleteCustomer(customer_id);
        return ResponseEntity.status(200).body("customer deleted!");
    }

    @GetMapping("/mycar/{booking_id}")
    public ResponseEntity Customer_Car(@PathVariable Integer booking_id, @AuthenticationPrincipal MyUser user){
        Car mycar =customerService.Customer_Car(booking_id,user);
        return ResponseEntity.status(200).body(mycar);
    }
}
