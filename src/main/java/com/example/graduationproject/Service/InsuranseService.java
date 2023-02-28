package com.example.graduationproject.Service;


import com.example.graduationproject.Exception.ApiException;
import com.example.graduationproject.Model.Car;
import com.example.graduationproject.Model.Insurance;
import com.example.graduationproject.Model.MyUser;
import com.example.graduationproject.Repository.CarRepository;
import com.example.graduationproject.Repository.InsuranseRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranseService {
    private final InsuranseRepositry insuranseRepositry;
    private final CarRepository carRepository;

    //all
    public List<Insurance> getInsurances(){
        return insuranseRepositry.findAll();
    }

    //alll
    public Insurance getInsurance(Integer id){
        Insurance Insurance = insuranseRepositry.findInsuranceById(id);
        if(Insurance==null){
            throw new ApiException("Insurance class not found");
        }

        return Insurance;
    }
    // owner
    public void AddInsurance(Insurance Insurance){
//        Car car = carRepository.findCarById(car_id);
//        if(car.getCarOwner().getMyUser().getId()!= user.getId()){
//            throw new ApiException("you have no authorty!");
//        }
        insuranseRepositry.save(Insurance);
    }
    //owner
    public void  UpdateInsurance( Insurance insurance,Integer Insurance_id){
        Insurance old_Insurance = insuranseRepositry.findInsuranceById(Insurance_id);
        if(old_Insurance==null) {
            throw new ApiException("Insurance id not found!!");
        }
       old_Insurance.setViolation_detail(insurance.getViolation_detail());
        old_Insurance.setViolation_image(insurance.getViolation_image());
        insuranseRepositry.save(old_Insurance);
    }
    //owner
    public void DeleteInsurance(Integer Insurance_id){
        Insurance delete_Insurance = insuranseRepositry.findInsuranceById(Insurance_id);
        if(delete_Insurance==null){
            throw new ApiException("Insurance_id id not found!!");
        }
        insuranseRepositry.delete(delete_Insurance);
    }}
