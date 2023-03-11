package com.example.carrental.Controller;


import com.example.carrental.Model.MyUser;
import com.example.carrental.Model.Violations;
import com.example.carrental.Service.ViolationsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/violations")
public class ViolationsController {
    private ViolationsService violationsService;

    public ViolationsController(ViolationsService violationsService){
        this.violationsService=violationsService;
    }
    @GetMapping("/all")
    public ResponseEntity GetAll(){
        List<Violations> violationsList = violationsService.AllViolations();
        return ResponseEntity.status(200).body(violationsList);
    }

    @PostMapping("/add")
    public ResponseEntity Addviolations(@Valid @RequestBody Violations violations){
        violationsService.AddViolations(violations);
        return ResponseEntity.status(200).body("violations added");
    }

    @PutMapping("/update/{violations_id}")
    public ResponseEntity update_violations(@Valid @RequestBody Violations violations , @PathVariable Integer violations_id,@AuthenticationPrincipal MyUser myUser){
        violationsService.UpdateViolations(violations_id,violations,myUser);
        return ResponseEntity.status(200).body("violations updated");
    }
    @DeleteMapping("/delete/{violations_id}")
    public ResponseEntity delete_violations(@PathVariable Integer violations_id,@AuthenticationPrincipal MyUser myUser){
        violationsService.DeleteViolations(violations_id,myUser);
        return ResponseEntity.status(200).body("violations deleted");
    }

    // only carowner assign
    @PutMapping("/assing/customer/{customer_id}/violations/{violations_id}")
    public ResponseEntity Assign(@PathVariable Integer customer_id ,@PathVariable Integer violations_id,@AuthenticationPrincipal MyUser user){
        violationsService.AssignViolationsToCustomer(customer_id,violations_id,user);
        return ResponseEntity.status(200).body("assign successfully");

    }

    // only customer
  @PutMapping("/payment/{violation_id}")
    public ResponseEntity payment_violations(@PathVariable Integer violation_id,@AuthenticationPrincipal MyUser user){
        violationsService.payment_violation(user,violation_id);
      return ResponseEntity.status(200).body("Thank you for paying your fine :)");
  }

  @PutMapping("/assign/{car_id}/{violation_id}")
    public ResponseEntity Assing(@PathVariable Integer car_id ,@PathVariable Integer violation_id,@AuthenticationPrincipal MyUser user){
        violationsService.AssignViolationsToCar(car_id,violation_id,user);
        return ResponseEntity.status(200).body("assign successfully");
  }

  @GetMapping("/unpaid/{violation_id}")
    public ResponseEntity Unpaid(@PathVariable Integer violation_id,@AuthenticationPrincipal MyUser user){
      return ResponseEntity.status(200).body(violationsService.ListOfUnPaidCustomer(violation_id,user));

  }
}
