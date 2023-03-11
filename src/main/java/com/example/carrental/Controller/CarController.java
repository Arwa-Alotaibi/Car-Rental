package com.example.carrental.Controller;

import com.example.carrental.Model.Car;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    private CarService carService;
    public CarController(CarService carService){
        this.carService=carService;
    }
    @GetMapping("/all")
    public ResponseEntity GetAll(){
        List<Car> carList = carService.GetAll();
        return ResponseEntity.status(200).body(carList);
    }
    @PostMapping("/add")
    public ResponseEntity AddCar(@Valid @RequestBody Car car, @AuthenticationPrincipal MyUser user){
        carService.AddCar(user,car);
        return ResponseEntity.status(200).body("car added!!");
    }
    @PutMapping("/update/{car_id}")
    public ResponseEntity updatecar(@Valid @RequestBody Car car, @PathVariable Integer car_id, @AuthenticationPrincipal MyUser user){
        carService.updatecar(car_id,car,user);
        return ResponseEntity.status(200).body("car updated!!");
    }

    @DeleteMapping("/delete/{car_id}")
    public ResponseEntity Delee_car(@PathVariable Integer car_id,@AuthenticationPrincipal MyUser user){
        carService.Delete_car(car_id,user);
        return ResponseEntity.status(200).body("car deleted!!");
    }
//
//    @PutMapping("/assing/{car_id}/{booking_id}")
//    public ResponseEntity Assign(@PathVariable Integer car_id, @PathVariable Integer booking_id){
//        carService.AsssignCarToBooking(car_id,booking_id);
//        return ResponseEntity.status(200).body("assign successfully!!");
//    }

//    @PutMapping("/assign/{carowner_id}/{car_id}")
//    public ResponseEntity AssignCarToOwner(@PathVariable Integer car_id , @PathVariable Integer carowner_id){
//        carService.AssignCarToOwner(car_id,carowner_id);
//        return ResponseEntity.status(200).body("assign successfully!!");
//    }


    @PutMapping("/assign/car/{car_id}/{insuranse_id}")
    public ResponseEntity AssignCarToInsuranse(@PathVariable Integer car_id ,@PathVariable Integer insuranse_id){
        carService.AssingInsuranceToCar(car_id,insuranse_id);
        return ResponseEntity.status(200).body("assign successfully!!");
    }

    @GetMapping("/ascending")
    public ResponseEntity ListCarAscendingByPrice(){
        List<Car> AscendingByPrice = carService.ListCarAscendingByPrice();
        return ResponseEntity.status(200).body(AscendingByPrice);
    }


    @GetMapping("/dscending")
    public ResponseEntity ListCarDscendingByPrice(){
        List<Car> DscendingByPrice = carService.ListCarDscendingByPrice();
        return ResponseEntity.status(200).body(DscendingByPrice);
    }

    @GetMapping("/owner/name/{car_id}")
    public ResponseEntity GetOwnerCar(@PathVariable Integer car_id){
        String onwer_name = carService.GetOwnerCar(car_id);
        return ResponseEntity.status(200).body(onwer_name);

    }


}
