package com.example.graduationproject.Service;

import com.example.graduationproject.Exception.ApiException;
import com.example.graduationproject.Model.Customer;
import com.example.graduationproject.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    //get all customer :)
    public List<Customer> GetAll(){
        return customerRepository.findAll();
    }

    // Add Customer :)
    public void AddCustomer(Customer customer){
        customerRepository.save(customer);
    }

    //update customer :)

    public void UpdateCustomer(Integer customer_id,Customer customer){
        Customer old_customer = customerRepository.findCustomersById(customer_id);
        if(old_customer==null){
            throw new ApiException("customer id not found!!");
        }
        old_customer.setName(customer.getName());
        old_customer.setAge(customer.getAge());
        old_customer.setBalance(customer.getBalance());
        old_customer.setPassword(customer.getPassword());
        old_customer.setLicense(customer.getLicense());
        old_customer.setEmail_address(customer.getEmail_address());
        old_customer.setRentdate(customer.getRentdate());
        old_customer.setReturndate(customer.getReturndate());
        old_customer.setPhone_number(customer.getPhone_number());
        customerRepository.save(old_customer);
    }

    //Delete Customer :)
    public void DeleteCustomer(Integer customer_id){
        Customer delete_customer =customerRepository.findCustomersById(customer_id);
        if(delete_customer==null){
            throw new ApiException("customer id not found!!");
        }
        customerRepository.delete(delete_customer);
    }
}
