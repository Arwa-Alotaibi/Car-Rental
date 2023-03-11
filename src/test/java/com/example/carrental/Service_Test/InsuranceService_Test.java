package com.example.carrental.Service_Test;
import com.example.carrental.Model.*;
import com.example.carrental.Repository.InsuranseRepositry;
import com.example.carrental.Repository.MyUserRepository;
import com.example.carrental.Service.InsuranseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class InsuranceService_Test {
    @InjectMocks
    InsuranseService insuranseService;

    @Mock
    InsuranseRepositry insuranseRepositry;
    @Mock
    MyUserRepository myUserRepository;



    MyUser myUser;

    Insurance insurance1, insurance2;

    List<Insurance> insurances;

    Car car;


    @BeforeEach
    void setUp() {
        myUser = new MyUser(null, "shaima", "Customer", "123",null , null);
        car=new Car(null,45.5,"economic",null,null,"blue",null,null,null,null,null);
        insurance1=new Insurance(null,"full insurance","fasa",null);
        insurance2 = new Insurance(null,"full insurance","fasa",null);
        insurances = new ArrayList<>();
        insurances.add(insurance1);
        insurances.add(insurance2);
    }

    @Test
    public void getAllinsuranse() {
        when(insuranseRepositry.findAll()).thenReturn(insurances);
        List<Insurance> insuranceList = insuranseService.getInsurances();
        Assertions.assertEquals(2, insuranceList.size());
        verify(insuranseRepositry, times(1)).findAll();
    }


    @Test
    public void addinsurance(){
        insuranseService.AddInsurance(insurance1);
        verify(insuranseRepositry,times(1)).save(insurance1);
    }
}
