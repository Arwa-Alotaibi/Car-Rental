package com.example.graduationproject.Repository;


import com.example.graduationproject.Model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranseRepositry extends JpaRepository<Insurance,Integer> {
    Insurance findInsuranceById(Integer id);
}
