package com.example.carrental.Service;

import com.example.carrental.Exception.ApiException;
import com.example.carrental.Model.*;
import com.example.carrental.Repository.CarRepository;
import com.example.carrental.Repository.CustomerRepository;
import com.example.carrental.Repository.ViolationsRepository;
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
    // get all admin - owner
    public List<Violations> AllViolations(){
       return violationsRepository.findAll();
    }

    //only owner and admin
    public void  AddViolations(Violations violations){
        violationsRepository.save(violations);
    }

    //only owner and admin
    public void UpdateViolations(Integer violations_id,Violations violations,MyUser user){
        Violations update_violations= violationsRepository.findViolationsById(violations_id);
        if(update_violations==null){
            throw new ApiException("violations id not found");}

          else if(user.getCarOwner().getMyUser().getId()!=user.getId()){
               throw new ApiException("you have no authorty");
           }

        update_violations.setViolation_date(violations.getViolation_date());
        update_violations.setViolation_type(violations.getViolation_type());
        update_violations.setPenality_fee(violations.getPenality_fee());
        violationsRepository.save(update_violations);
    }

    //only owner and admin
    public void DeleteViolations(Integer violations_id,MyUser myUser){
        Violations delete_violations= violationsRepository.findViolationsById(violations_id);
        if(delete_violations==null){
            throw new ApiException("violations id not found");
        }
        else if(myUser.getCarOwner().getMyUser().getId()!=myUser.getId()){
            throw new ApiException("you have no authorty");
        }
        violationsRepository.delete(delete_violations);
    }


    // only owner and admin
    public void  AssignViolationsToCustomer(Integer customer_id,Integer violation_id ,MyUser myUser){
        Customer customer = customerRepository.findCustomersById(customer_id);
        Violations violations = violationsRepository.findViolationsById(violation_id);
        if(violations ==null || customer==null){
            throw new ApiException("violations id not found or customer id not found");
        }else if(myUser.getCarOwner().getMyUser().getId()!=myUser.getId()){
            throw new ApiException("you have no authorty");
        }
        violations.getCustomers().add(customer);
        customer.getViolations_list().add(violations);
        violationsRepository.save(violations);
        customerRepository.save(customer);
    }

    // customer onlyy
    public void payment_violation(MyUser user, Integer violation_id){
        Customer customer = customerRepository.findCustomerByMyUserId(user.getId());
        Violations violations = violationsRepository.findViolationsById(violation_id);

        if(violations ==null ){
            throw new ApiException("violations id not found or customer id not found");
        }
        //حطيتها لأنه يسوي دفع وهو مافيه ربط بينهم
        else if(customer.getViolations_list().isEmpty()){
            throw new ApiException("The customer has no violations");
        }
            if (customer.getMyUser().getId()!= user.getId()){
                throw new ApiException("you do not have auth");
            }


        else if(customer.getBalance()< violations.getPenality_fee()){
            throw new ApiException("You can't pay the fine because your balance is low !! ");
        }

        customer.setBalance(customer.getBalance()-violations.getPenality_fee());
        customer.getViolations_list().remove(violations);
        violations.getCustomers().remove(customer);
        customerRepository.save(customer);
    }



    public void AssignViolationsToCar(Integer car_id,Integer violation_id,MyUser myUser){
        Car car = carRepository.findCarById(car_id);
        Violations violations = violationsRepository.findViolationsById(violation_id);
        if(car.getCarOwner().getId()!= myUser.getId()){
            throw new ApiException("you have no authority");
        }
        if(violations ==null || car==null  ){
            throw new ApiException("violations id not found or car id not found");
        }

        violations.setCars(car);
        violationsRepository.save(violations);
    }


    public List<Customer> ListOfUnPaidCustomer(Integer violation_id,MyUser myUser){
       Violations violations = violationsRepository.findViolationsById(violation_id);
        if(carRepository.findCarById(myUser.getId()).getCarOwner().getId()!= myUser.getId()){
            throw new ApiException("you have no authority");
        }
        if(violations.getCustomers().isEmpty()){
            throw new ApiException("Wow all customers paid the violations :)");
        }
        return violations.getCustomers();
    }




}
