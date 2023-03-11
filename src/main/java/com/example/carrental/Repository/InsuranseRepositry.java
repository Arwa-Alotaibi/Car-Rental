package com.example.carrental.Repository;


import com.example.carrental.Model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranseRepositry extends JpaRepository<Insurance,Integer> {
    Insurance findInsuranceById(Integer id);
}
