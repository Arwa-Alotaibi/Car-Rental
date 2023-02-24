package com.example.graduationproject.Service;

import com.example.graduationproject.Exception.ApiException;
import com.example.graduationproject.Model.Booking_Order;
import com.example.graduationproject.Model.Car;
import com.example.graduationproject.Model.CarOwner;
import com.example.graduationproject.Model.Insurance;
import com.example.graduationproject.Repository.Booking_OrderRepository;
import com.example.graduationproject.Repository.CarOwnerRepositry;
import com.example.graduationproject.Repository.CarRepository;
import com.example.graduationproject.Repository.InsuranseRepositry;
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
    public List<Car> GetAll(){
        return carRepository.findAll();
    }

    public void AddCar(Car car){
        carRepository.save(car);
    }
    public void updatecar(Integer car_id, Car car){
        Car update_car = carRepository.findCarById(car_id);
        if(update_car==null){
            throw new ApiException("Car id not found");
        }
        update_car.setCar_class(car.getCar_class());
        update_car.setCar_history(car.getCar_history());
        update_car.setColor(car.getColor());
        update_car.setPrice(car.getPrice());
        update_car.setAvailable_date(car.getAvailable_date());
        carRepository.save(update_car);
    }

    public void Delete_car(Integer car_id){
        Car delete_car = carRepository.findCarById(car_id);
        if(delete_car==null){
            throw new ApiException("Car id not found");
        }
        carRepository.delete(delete_car);
    }
//
//    public void AsssignCarToBooking(Integer car_id,Integer booking_id){
//        Car car = carRepository.findCarById(car_id);
//        Booking_Order bookingOrder = bookingOrderRepository.findBooking_OrderById(booking_id);
//        if(car==null || bookingOrder==null){
//            throw new ApiException("car id not found or booking id id not found");
//        }
//        car.setBookingOrder(bookingOrder);
//        carRepository.save(car);
//    }


    public void AssignCarToOwner(Integer car_id , Integer carowner_id){
        Car car = carRepository.findCarById(car_id);
        CarOwner carOwner = carOwnerRepositry.findCarOwnerById(carowner_id);
        if(car==null || carOwner==null){
            throw new ApiException("car id not found or carOwner id id not found");
        }
        car.setCarOwner(carOwner);
        carRepository.save(car);
    }


    public void  AssingInsuranceToCar(Integer car_id, Integer insuranse_id){
        Car car =carRepository.findCarById(car_id);
        Insurance insurance = insuranseRepositry.findInsuranceById(insuranse_id);
        if(car==null || insurance==null){
            throw new ApiException("car id not found or insurance id id not found");
        }
        car.setInsurance(insurance);
        carRepository.save(car);
    }

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

