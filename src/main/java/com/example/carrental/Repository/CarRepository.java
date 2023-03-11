package com.example.carrental.Repository;

import com.example.carrental.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    Car findCarById(Integer id);

    List<Car> findByOrderByPriceAsc();

    List<Car> findByOrderByPriceDesc();

}
