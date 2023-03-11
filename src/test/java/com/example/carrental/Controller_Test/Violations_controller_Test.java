package com.example.carrental.Controller_Test;

import com.example.carrental.Controller.ViolationsController;
import com.example.carrental.Exception.ApiException;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Model.Violations;
import com.example.carrental.Service.ViolationsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

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
