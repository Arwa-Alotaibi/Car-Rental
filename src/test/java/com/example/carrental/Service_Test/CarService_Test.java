package com.example.carrental.Service_Test;
import com.example.carrental.Model.*;
import com.example.carrental.Repository.CarRepository;
import com.example.carrental.Repository.MyUserRepository;
import com.example.carrental.Service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CarService_Test {
    @InjectMocks
    CarService carService;

    @Mock
    MyUserRepository myUserRepository;

    MyUser user;
    @Mock
    CarRepository carRepository;
    Car   car1 , car3;

    CarOwner carOwner;
    List<Car> cars;

    @BeforeEach
    void setUp() {
        carOwner = new CarOwner(null,"arwa","arwa@gmail.com",2222,"no","no","aa",44,null,user,null);
        user= new MyUser(null,"aa","Owner","111",null,carOwner);
        car1 = new Car(null,11.5,"classic",null,"no","black",null,null,null,null,null);
        car3 = new Car(null,11.5,"classic",null,"no","black",null,null,null,null,null);

        cars=new ArrayList<>();
        cars.add(car1);
        cars.add(car3);
    }

    @Test
    public void getAllCar() {
        when(carRepository.findAll()).thenReturn(cars);
        List<Car> carList = carService.GetAll();
        Assertions.assertEquals(2, carList.size());
        verify(carRepository, times(1)).findAll();
    }


    }
