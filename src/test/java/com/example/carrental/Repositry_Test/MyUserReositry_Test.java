package com.example.carrental.Repositry_Test;


import com.example.carrental.Model.MyUser;
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
public class MyUserReositry_Test {

    @Autowired
    MyUserRepository myUserRepository;
    MyUser myUser;
    @BeforeEach
    void setUp() {
        myUser=new MyUser(null,"shaima","Admin","123",null,null);
    }

    @Test
    public void findMyUserById(){
        myUserRepository.save(myUser);
        MyUser myUser1 = myUserRepository.findMyUsersById(myUser.getId());
        Assertions.assertThat(myUser1).isEqualTo(myUser);
    }
}
