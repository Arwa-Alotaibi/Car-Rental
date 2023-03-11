package com.example.carrental.Repositry_Test;
import com.example.carrental.Model.Insurance;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Repository.InsuranseRepositry;
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
public class InsuranseRepositryTest {
    @Autowired
    InsuranseRepositry insuranseRepositry;

    @Autowired
    MyUserRepository myUserRepository;
    Insurance insurance1;

    MyUser myUser;


    @BeforeEach
    void setUp() {
        myUser=new MyUser(null,"arwa_11","Owner","12345",null,null);
        insurance1 = new Insurance(null,"no","no",null);

    }
    @Test
    public void findInsuranseRepositryById(){
        insuranseRepositry.save(insurance1);
        Insurance insurance=insuranseRepositry.findInsuranceById(insurance1.getId());
        Assertions.assertThat(insurance).isEqualTo(insurance1);
    }

}
