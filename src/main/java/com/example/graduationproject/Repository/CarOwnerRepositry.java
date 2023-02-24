package com.example.graduationproject.Repository;

import com.example.graduationproject.Model.CarOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnerRepositry extends JpaRepository<CarOwner,Integer> {
    CarOwner findCarOwnerById(Integer id);
}
