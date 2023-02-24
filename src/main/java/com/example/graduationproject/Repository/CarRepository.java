package com.example.graduationproject.Repository;

import com.example.graduationproject.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    Car findCarById(Integer id);
}
