package com.example.graduationproject.Controller;


import com.example.graduationproject.Model.IsReserved;
import com.example.graduationproject.Service.IsReservedService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/isReserved")
@RequiredArgsConstructor
public class IsReservedController {
    private final IsReservedService isReservedService;
    @GetMapping("/all")
    public ResponseEntity GetAllisReserved(){
        List<IsReserved> isReserveds = isReservedService.getisReserveds();
        return ResponseEntity.status(200).body(isReserveds);
    }
    //Add Customer
    @PostMapping("/add")
    public ResponseEntity AddisReserved(@Valid @RequestBody IsReserved isReserved){
        isReservedService.AddisReserved(isReserved);
        return ResponseEntity.status(200).body("isReserved added");
    }
    @PutMapping("/update/{isReserved_id}")
    public ResponseEntity UpdateisReserved(@Valid @RequestBody IsReserved isReserved , @PathVariable Integer isReserved_id){
        isReservedService.UpdateisReserved(isReserved, isReserved_id);
        return ResponseEntity.status(200).body("isReserved updated");
    }
    @DeleteMapping("/delete/{isReserved_id}")
    public ResponseEntity DeleteisReserved(@PathVariable Integer isReserved_id){
        isReservedService.DeleteisReserved(isReserved_id);
        return ResponseEntity.status(200).body("isReserved deleted");
    }
    @PutMapping("/assing/{isReserved_id}/{car_id}")
    public ResponseEntity Assing(@PathVariable Integer isReserved_id , @PathVariable Integer car_id){
        isReservedService.Assign(isReserved_id,car_id);
        return ResponseEntity.status(200).body("assign successfully");
    }

}


