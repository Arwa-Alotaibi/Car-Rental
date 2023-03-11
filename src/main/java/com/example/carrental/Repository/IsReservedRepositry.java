package com.example.carrental.Repository;

import com.example.carrental.Model.IsReserved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IsReservedRepositry extends JpaRepository<IsReserved,Integer> {
    IsReserved findIsReservedById(Integer id);

    List<IsReserved> findAllByCar_Id(Integer id);
}
