package com.example.carrental.Repositry_Test;
import com.example.carrental.Model.Car;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Repository.CarRepository;
import com.example.carrental.Repository.MyUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
