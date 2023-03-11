package com.example.carrental.Service;

import com.example.carrental.Exception.ApiException;
import com.example.carrental.Model.Car;
import com.example.carrental.Model.CarOwner;
import com.example.carrental.Repository.CarOwnerRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarOwnerService {
    private final CarOwnerRepositry carOwnerRepositry;

    public List<CarOwner> getCarOwners(){

        return carOwnerRepositry.findAll();
    }
    public CarOwner getCarOwner(Integer id){
        CarOwner carOwner = carOwnerRepositry.findCarOwnerById(id);
        if(carOwner==null){
            throw new ApiException("carOwner class not found");
        }

        return carOwner;
    }
    public void AddcarOwner(CarOwner carOwner){

        carOwnerRepositry.save(carOwner);
    }

    public void  UpdateCarOwner( CarOwner carOwner,Integer carOwner_id){
        CarOwner old_carOwner = carOwnerRepositry.findCarOwnerById(carOwner_id);
        if(old_carOwner==null) {
            throw new ApiException("carOwner id not found!!");
        }
        old_carOwner.setName(carOwner.getName());
        old_carOwner.setCustomerInfo(carOwner.getCustomerInfo());
        old_carOwner.setCarAvailabilty(carOwner.getCarAvailabilty());
        old_carOwner.setBookingRequest(carOwner.getBookingRequest());
        old_carOwner.setInvoice_Details(carOwner.getInvoice_Details());
        old_carOwner.setReturn_Date(carOwner.getReturn_Date());
        old_carOwner.setPhone_Number(carOwner.getPhone_Number());
        old_carOwner.setEmail(carOwner.getEmail());
     //   old_carOwner.setPassword(carOwner.getPassword());
        carOwnerRepositry.save(old_carOwner);
    }
    public void DeleteCarOwner(Integer carOwner_id){
        CarOwner delete_carOwner = carOwnerRepositry.findCarOwnerById(carOwner_id);
        if(delete_carOwner==null){
            throw new ApiException("car owner id not found!!");
        }

        carOwnerRepositry.delete(delete_carOwner);
    }


    public List<Car>findAllCarByOwnerName(String owner_name){
        CarOwner carOwner = carOwnerRepositry.findCarOwnerByName(owner_name);
        if(carOwner==null){
            throw new ApiException("owner name not found");
        }
        return carOwner.getCarList();
    }
}
