package com.example.carrental.Repositry_Test;
import com.example.carrental.Model.CarOwner;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Repository.CarOwnerRepositry;
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
