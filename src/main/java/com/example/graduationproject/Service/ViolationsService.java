package com.example.graduationproject.Service;

import com.example.graduationproject.Exception.ApiException;
import com.example.graduationproject.Model.Car;
import com.example.graduationproject.Model.Customer;
import com.example.graduationproject.Model.Violations;
import com.example.graduationproject.Repository.CarRepository;
import com.example.graduationproject.Repository.CustomerRepository;
import com.example.graduationproject.Repository.ViolationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViolationsService {
    private ViolationsRepository violationsRepository;
    private CustomerRepository customerRepository;

    private CarRepository carRepository;
    public ViolationsService(ViolationsRepository violationsRepository,CustomerRepository customerRepository,CarRepository carRepository){
        this.violationsRepository=violationsRepository;
        this.customerRepository=customerRepository;
        this.carRepository=carRepository;
    }
    // get all
    public List<Violations> AllViolations(){
       return violationsRepository.findAll();
    }

    //Add Violations
    public void  AddViolations(Violations violations){
        violationsRepository.save(violations);
    }

    //update Violations
    public void UpdateViolations(Integer violations_id,Violations violations){
        Violations update_violations= violationsRepository.findViolationsById(violations_id);
        if(update_violations==null){
            throw new ApiException("violations id not found");
        }
        update_violations.setViolation_date(violations.getViolation_date());
        update_violations.setViolation_type(violations.getViolation_type());
        update_violations.setPenality_fee(violations.getPenality_fee());
        violationsRepository.save(update_violations);
    }

    //delete Violations
    public void DeleteViolations(Integer violations_id){
        Violations delete_violations= violationsRepository.findViolationsById(violations_id);
        if(delete_violations==null){
            throw new ApiException("violations id not found");
        }
        violationsRepository.delete(delete_violations);
    }


    // only car_owner assign
    public void  AssignViolationsToCustomer(Integer customer_id,Integer violation_id ){
        Customer customer = customerRepository.findCustomersById(customer_id);
        Violations violations = violationsRepository.findViolationsById(violation_id);
        if(violations ==null || customer==null){
            throw new ApiException("violations id not found or customer id not found");
        }
        violations.getCustomers().add(customer);
        customer.getViolations_list().add(violations);
        violationsRepository.save(violations);
        customerRepository.save(customer);
    }

    public void payment_violation(Integer customer_id,Integer violation_id){
        Customer customer = customerRepository.findCustomersById(customer_id);
        Violations violations = violationsRepository.findViolationsById(violation_id);

        if(violations ==null || customer==null  ){
            throw new ApiException("violations id not found or customer id not found");
        }
        //حطيتها لأنه يسوي دفع وهو مافيه ربط بينهم
        else if(customer.getViolations_list().isEmpty()){
            throw new ApiException("The customer has no violations");
        }
       else if(customer.getBalance()< violations.getPenality_fee()){
            throw new ApiException("You can't pay the fine because your balance is low !! ");
        }
        customer.setBalance(customer.getBalance()-violations.getPenality_fee());
        customer.getViolations_list().remove(violations);
        violations.getCustomers().remove(customer);
        customerRepository.save(customer);
    }



    public void AssignViolationsToCar(Integer car_id,Integer violation_id){
        Car car = carRepository.findCarById(car_id);
        Violations violations = violationsRepository.findViolationsById(violation_id);

        if(violations ==null || car==null  ){
            throw new ApiException("violations id not found or car id not found");
        }

        violations.setCars(car);
        violationsRepository.save(violations);
    }


    public List<Customer> ListOfUnPaidCustomer(Integer violation_id){
       Violations violations = violationsRepository.findViolationsById(violation_id);
        if(violations.getCustomers().isEmpty()){
            throw new ApiException("Wow all customers paid the violations :)");
        }
        return violations.getCustomers();
    }



}
