package com.example.carrental.Repository;

import com.example.carrental.Model.CarOwner;
import com.example.carrental.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnerRepositry extends JpaRepository<CarOwner,Integer> {
    CarOwner findCarOwnerById(Integer id);
    CarOwner findCarOwnerByName(String name);

    CarOwner findCarOwnerByMyUserId(Integer id);

    CarOwner findCarOwnerByMyUser(MyUser myUser);
}

