package com.example.graduationproject.Controller;

import com.example.graduationproject.DTO.CustomerDTO;
import com.example.graduationproject.DTO.OwnerDTO;
import com.example.graduationproject.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class MyUserController {
    private final MyUserService myUserService;

    @PostMapping("/login")
    public ResponseEntity Login(){
        return ResponseEntity.status(200).body("Logged in successfully");
    }


    @PostMapping("/register/owner")
    public ResponseEntity Register_Owner(@Valid @RequestBody OwnerDTO ownerDTO){
        myUserService.AddOwner(ownerDTO);
        return ResponseEntity.status(200).body("You have been registered successfully");
    }
    @PostMapping("/register/customer")
    public ResponseEntity Register_Customer(@Valid @RequestBody CustomerDTO customerDTO){
        myUserService.AddCustomer(customerDTO);
        return ResponseEntity.status(200).body("You have been registered successfully");

    }
}
