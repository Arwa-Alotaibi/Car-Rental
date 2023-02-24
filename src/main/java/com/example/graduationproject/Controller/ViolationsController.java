package com.example.graduationproject.Controller;


import com.example.graduationproject.Model.Violations;
import com.example.graduationproject.Repository.ViolationsRepository;
import com.example.graduationproject.Service.ViolationsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity update_violations(@Valid @RequestBody Violations violations , @PathVariable Integer violations_id){
        violationsService.UpdateViolations(violations_id,violations);
        return ResponseEntity.status(200).body("violations updated");
    }
    @DeleteMapping("/delete/{violations_id}")
    public ResponseEntity delete_violations(@PathVariable Integer violations_id){
        violationsService.DeleteViolations(violations_id);
        return ResponseEntity.status(200).body("violations deleted");
    }

    // only carowner assign
    @PutMapping("/assing/customer/{customer_id}/violations/{violations_id}")
    public ResponseEntity Assign(@PathVariable Integer customer_id ,@PathVariable Integer violations_id){
        violationsService.AssignViolationsToCustomer(customer_id,violations_id);
        return ResponseEntity.status(200).body("assign successfully");

    }

  @PutMapping("/payment/{customer_id}/{violation_id}")
    public ResponseEntity payment_violations(@PathVariable Integer customer_id,@PathVariable Integer violation_id){
        violationsService.payment_violation(customer_id,violation_id);
      return ResponseEntity.status(200).body("Thank you for paying your fine :)");
  }

  @PutMapping("/assign/{car_id}/{violation_id}")
    public ResponseEntity Assing(@PathVariable Integer car_id ,@PathVariable Integer violation_id){
        violationsService.AssignViolationsToCar(car_id,violation_id);
        return ResponseEntity.status(200).body("assign successfully");
  }

  @GetMapping("/unpaid/{violation_id}")
    public ResponseEntity Unpaid(@PathVariable Integer violation_id){
      return ResponseEntity.status(200).body(violationsService.ListOfUnPaidCustomer(violation_id));

  }
}
