package com.example.carrental.Service;


import com.example.carrental.Exception.ApiException;
import com.example.carrental.Model.Insurance;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Repository.CarRepository;
import com.example.carrental.Repository.InsuranseRepositry;
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
        insuranseRepositry.save(Insurance);
    }
    //owner
    public void  UpdateInsurance( Insurance insurance,Integer Insurance_id,MyUser user){
        Insurance old_Insurance = insuranseRepositry.findInsuranceById(Insurance_id);
        if(old_Insurance==null) {
            throw new ApiException("Insurance id not found!!");
        } else if (user.getRole().equals("Owner")) {
            if(user.getCarOwner().getMyUser().getId()!= user.getId()){
                throw new ApiException("YOU Dont have auth");
            }

        }
        old_Insurance.setViolation_detail(insurance.getViolation_detail());
        old_Insurance.setViolation_image(insurance.getViolation_image());
        insuranseRepositry.save(old_Insurance);
    }
    //owner
    public void DeleteInsurance(Integer Insurance_id,MyUser user){
        Insurance delete_Insurance = insuranseRepositry.findInsuranceById(Insurance_id);
        if(delete_Insurance==null){
            throw new ApiException("Insurance_id id not found!!");
        }
        else if (user.getRole().equals("Owner")) {
            if(user.getCarOwner().getMyUser().getId()!= user.getId()){
                throw new ApiException("YOU Dont have auth");
            }

        }
        insuranseRepositry.delete(delete_Insurance);
    }}
