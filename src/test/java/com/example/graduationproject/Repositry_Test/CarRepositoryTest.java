package com.example.graduationproject.Repositry_Test;
import com.example.graduationproject.Model.Car;
import com.example.graduationproject.Model.MyUser;
import com.example.graduationproject.Repository.CarRepository;
import com.example.graduationproject.Repository.MyUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarRepositoryTest {
    @Autowired
    CarRepository carRepository;

    @Autowired
    MyUserRepository myUserRepository;

    Car car1, car2, car3;

    MyUser myUser;


    @BeforeEach
    void setUp() {
        myUser=new MyUser(null,"arwa_11","Owner","12345",null,null);
        car1 = new Car(null,11.5,"classic",null,"no","black",null,null,null,myUser.getCarOwner(),null);

    }
    @Test
    public void findCarById(){
        carRepository.save(car1);
        Car car=carRepository.findCarById(car1.getId());
        Assertions.assertThat(car).isEqualTo(car1);
    }


}
