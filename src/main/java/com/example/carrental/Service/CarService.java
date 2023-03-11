package com.example.carrental.Service;

import com.example.carrental.Exception.ApiException;
import com.example.carrental.Model.*;
import com.example.carrental.Repository.Booking_OrderRepository;
import com.example.carrental.Repository.CarOwnerRepositry;
import com.example.carrental.Repository.CarRepository;
import com.example.carrental.Repository.InsuranseRepositry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private CarRepository carRepository ;
    private Booking_OrderRepository bookingOrderRepository;

    private CarOwnerRepositry carOwnerRepositry;
    private InsuranseRepositry insuranseRepositry ;

    public CarService(CarRepository carRepository,Booking_OrderRepository bookingOrderRepository,CarOwnerRepositry carOwnerRepositry, InsuranseRepositry insuranseRepositry  ){
        this.carRepository=carRepository;
        this.bookingOrderRepository=bookingOrderRepository;
        this.carOwnerRepositry=carOwnerRepositry;
        this.insuranseRepositry=insuranseRepositry;
    }
    // all users can see all cars:)
    public List<Car> GetAll(){
        return carRepository.findAll();
    }

    //owner only owner can add car :)
    public void AddCar(MyUser user, Car car){
        CarOwner owner = carOwnerRepositry.findCarOwnerById(user.getId());

        car.setCarOwner(owner);
        carRepository.save(car);
    }
    //owner only owner can update car :)

    public void updatecar(Integer car_id, Car car,MyUser user){
        Car update_car = carRepository.findCarById(car_id);
        if(update_car==null){
            throw new ApiException("Car id not found");
        }
        if (user.getRole().equals("Owner")) {
            if (update_car.getCarOwner().getMyUser().getId() != user.getId()) {
                throw new ApiException("you do not have auth");
            }
        }
        update_car.setCar_class(car.getCar_class());
        update_car.setCar_history(car.getCar_history());
        update_car.setColor(car.getColor());
        update_car.setPrice(car.getPrice());
        update_car.setAvailable_date(car.getAvailable_date());
        carRepository.save(update_car);
    }

    // only owner can delete car :)
    public void Delete_car(Integer car_id,MyUser user){
        Car delete_car = carRepository.findCarById(car_id);
        if(delete_car==null){
            throw new ApiException("Car id not found");
        }
        else  if (user.getRole().equals("Owner")) {
            if (delete_car.getCarOwner().getMyUser().getId() != user.getId()) {
                throw new ApiException("you do not have auth");
            }
        }

        carRepository.delete(delete_car);
    }

    // only owner can assign

    public void  AssingInsuranceToCar(Integer car_id, Integer insuranse_id){
        Car car =carRepository.findCarById(car_id);
        Insurance insurance = insuranseRepositry.findInsuranceById(insuranse_id);
        if(car==null || insurance==null){
            throw new ApiException("car id not found or insurance id id not found");
        }
        car.setInsurance(insurance);
        carRepository.save(car);
    }


    // all users can see asc and des :)
    //تعرض قائمة في اقل الاسعار
   public List<Car> ListCarAscendingByPrice(){
      return carRepository.findByOrderByPriceAsc();
   }
    //تعرض قائمة في اعلى الاسعار
   public List<Car>ListCarDscendingByPrice(){
        return carRepository.findByOrderByPriceDesc();
   }

   // GetOwnerCar
    public String GetOwnerCar(Integer car_id){
        Car car =carRepository.findCarById(car_id);
        if(car==null){
            throw new ApiException("car id not found");
        }
       return car.getCarOwner().getName();
    }

}

