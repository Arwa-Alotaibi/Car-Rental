package com.example.carrental.Repository;


import com.example.carrental.Model.Violations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViolationsRepository extends JpaRepository<Violations,Integer> {

   Violations findViolationsById(Integer id);


}
