package com.example.graduationproject.Controller_Test;

import com.example.graduationproject.Controller.Booking_OrderController;
import com.example.graduationproject.Controller.ViolationsController;
import com.example.graduationproject.Exception.ApiException;
import com.example.graduationproject.Model.Booking_Order;
import com.example.graduationproject.Model.MyUser;
import com.example.graduationproject.Model.Violations;
import com.example.graduationproject.Service.ViolationsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ViolationsController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class Violations_controller_Test {
    @MockBean
    ViolationsService violationsService;

    @Autowired
    MockMvc mockMvc;

    Violations violation1,violations2,violations3;
    MyUser myUser;

    ApiException apiResponse;


    List<Violations> violations,violationsList;

    @BeforeEach
    void setUp() {
        myUser=new MyUser(1,"arwa","Admin","11111",null,null);
        violation1 = new Violations(1,"cut of signal :)",null,10.4,null,null);
        violations2 = new Violations(2,"cut of signal",null,10.4,null,null);
        violations3 = new Violations(3,"cut of signal",null,10.4,null,null);
        violations= Arrays.asList(violation1);
        violationsList=Arrays.asList(violations2);

    }
    @Test
    public void testAddViolation() throws  Exception {
        mockMvc.perform(post("/api/v1/violations/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(violation1)))
                .andExpect(status().isOk());

    }
    @Test
    public void testDeleteViolation() throws Exception{
        mockMvc.perform(delete("/api/v1/violations/delete/{violations_id}",violation1.getId()))
                .andExpect(status().isOk());

    }




}
