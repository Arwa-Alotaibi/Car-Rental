package com.example.graduationproject.Repository;


import com.example.graduationproject.Model.Customer;
import com.example.graduationproject.Model.Violations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolationsRepository extends JpaRepository<Violations,Integer> {

   Violations findViolationsById(Integer id);

   Violations findViolationsByViolation_type(String violation_type);

}
