package com.example.graduationproject.Controller;


import com.example.graduationproject.Model.Insurance;
import com.example.graduationproject.Service.InsuranseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/insurance")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranseService insuranseService;
    @GetMapping("/all")
    public ResponseEntity GetAllInsurance(){
        List<Insurance> Insurances = insuranseService.getInsurances();
        return ResponseEntity.status(200).body(Insurances);
    }
    @PostMapping("/add")
    public ResponseEntity AddInsurance(@Valid @RequestBody Insurance insurance){
        insuranseService.AddInsurance(insurance);
        return ResponseEntity.status(200).body("Insurance added");
    }
    @PutMapping("/update/{Insurance_id}")
    public ResponseEntity UpdateInsurance(@Valid @RequestBody Insurance insurance , @PathVariable Integer Insurance_id){
        insuranseService.UpdateInsurance(insurance, Insurance_id);
        return ResponseEntity.status(200).body("Insurance updated");
    }
    @DeleteMapping("/delete/{Insurance_id}")
    public ResponseEntity DeleteInsurance(@PathVariable Integer Insurance_id){
        insuranseService.DeleteInsurance(Insurance_id);
        return ResponseEntity.status(200).body("Insurance deleted");
    }}
