package com.example.carrental.Service;

import com.example.carrental.Exception.ApiException;
import com.example.carrental.Model.Booking_Order;
import com.example.carrental.Model.Car;
import com.example.carrental.Model.Customer;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Repository.Booking_OrderRepository;
import com.example.carrental.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private Booking_OrderRepository bookingOrderRepository;
    public CustomerService(CustomerRepository customerRepository, Booking_OrderRepository bookingOrderRepository){
        this.customerRepository=customerRepository;
        this.bookingOrderRepository=bookingOrderRepository;
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
        //old_customer.setPassword(customer.getPassword());
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
    public Car Customer_Car(Integer booking_id,MyUser user){
        Customer customer = customerRepository.findCustomerByMyUserId(user.getId());
        Booking_Order bookingOrder = bookingOrderRepository.findBooking_OrderById(booking_id);
        if(bookingOrder==null){
            throw new ApiException("bookingOrder id not found!!");
        }
        return bookingOrder.getCar();
    }
}


