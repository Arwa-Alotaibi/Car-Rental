package com.example.graduationproject.Repositry_Test;
import com.example.graduationproject.Model.Car;
import com.example.graduationproject.Model.CarOwner;
import com.example.graduationproject.Model.MyUser;
import com.example.graduationproject.Repository.CarOwnerRepositry;
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
public class CarOwnerRepositryTest {

    @Autowired
    CarOwnerRepositry carOwnerRepositry;



    CarOwner carOwner;

    @Autowired
    MyUserRepository myUserRepository;

    MyUser myUser1;

    @BeforeEach
    void setUp() {
        carOwner=new CarOwner(null,"arwa","arwa@gmail.com",055555,"no","no","no",11,null,null,null);

    }
    @Test
    public void findOwnerById(){
        carOwnerRepositry.save(carOwner);
        CarOwner owner=carOwnerRepositry.findCarOwnerById(carOwner.getId());
        Assertions.assertThat(owner).isEqualTo(carOwner);
    }



}
