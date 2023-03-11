package com.example.carrental.Repository;


import com.example.carrental.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {
    MyUser findMyUsersById(Integer id);
    MyUser findMyUsersByUsername(String username);
}
