package com.example.carrental.Controller;

import com.example.carrental.DTO.CustomerDTO;
import com.example.carrental.DTO.OwnerDTO;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(200).body("You have been registered successfully :)");
    }
    @PostMapping("/register/customer")
    public ResponseEntity Register_Customer(@Valid @RequestBody CustomerDTO customerDTO){
        myUserService.AddCustomer(customerDTO);
        return ResponseEntity.status(200).body("You have been registered successfully :)");
    }
    @PutMapping("/update/customer")
    public ResponseEntity Update_Customer(@Valid @RequestBody CustomerDTO customerDTO , @AuthenticationPrincipal MyUser user_id){
        myUserService.update_customer(customerDTO, user_id.getId());
        return ResponseEntity.status(200).body("Customer updated :)");
    }

}
