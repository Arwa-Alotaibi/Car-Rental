package com.example.graduationproject.Service;


import com.example.graduationproject.Exception.ApiException;
import com.example.graduationproject.Model.Car;
import com.example.graduationproject.Model.IsReserved;
import com.example.graduationproject.Repository.CarRepository;
import com.example.graduationproject.Repository.IsReservedRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IsReservedService {
    private final IsReservedRepositry isReservedRepositry;
    private final CarRepository carRepository;
    public List<IsReserved> getisReserveds(){

        return isReservedRepositry.findAll();
    }
    public IsReserved getisReserved(Integer id){
        IsReserved isReserved = isReservedRepositry.findIsReservedById(id);
        if(isReserved==null){
            throw new ApiException("isReserved class not found");
        }

        return isReserved;
    }
    public void AddisReserved(IsReserved isReserved){
//        IsReserved isReserved1 = isReservedRepositry.findIsReservedById(isReserved.getId());
//        if(isReserved1==null){
//            throw new ApiException("isReserved class not found");
//        }
        isReservedRepositry.save(isReserved);
    }
    public void  UpdateisReserved( IsReserved isReserved,Integer isReserved_id){
        IsReserved old_isReserved = isReservedRepositry.findIsReservedById(isReserved_id);
        if(old_isReserved==null) {
            throw new ApiException("isReserved id not found!!");
        }
        old_isReserved.setReserved_Date(isReserved.getReserved_Date());


        isReservedRepositry.save(old_isReserved);
    }
    public void DeleteisReserved(Integer isReserved_id){
        IsReserved delete_isReserved = isReservedRepositry.findIsReservedById(isReserved_id);
        if(delete_isReserved==null){
            throw new ApiException(" id not found!!");
        }
        isReservedRepositry.delete(delete_isReserved);
    }

    public void Assign(Integer isReserved_id , Integer car_id){
        IsReserved isReserved = isReservedRepositry.findIsReservedById(isReserved_id);
        Car car = carRepository.findCarById(car_id);
        if(isReserved==null || car==null){
            throw new ApiException("isReserved id not found or car id id not found");
        }
        isReserved.setCar(car);
        isReservedRepositry.save(isReserved);
    }

}
