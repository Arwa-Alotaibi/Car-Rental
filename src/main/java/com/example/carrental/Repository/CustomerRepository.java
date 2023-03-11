package com.example.carrental.Repository;

import com.example.carrental.Model.Customer;
import com.example.carrental.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findCustomersById(Integer id);


    Customer findCustomerById(Integer id);

    Customer findCustomerByMyUser(MyUser myUser);

    Customer findCustomerByMyUserId(Integer id);


}
