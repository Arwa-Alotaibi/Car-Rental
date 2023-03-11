package com.example.carrental.Service;

import com.example.carrental.DTO.CustomerDTO;
import com.example.carrental.DTO.OwnerDTO;
import com.example.carrental.Exception.ApiException;
import com.example.carrental.Model.CarOwner;
import com.example.carrental.Model.Customer;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Repository.CarOwnerRepositry;
import com.example.carrental.Repository.CustomerRepository;
import com.example.carrental.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MyUserService {
    private final MyUserRepository myUserRepository;
    private final CustomerRepository customerRepository;
    private final CarOwnerRepositry carOwnerRepositry;
    private final  CustomerService customerService;
    private final  CarOwnerService carOwnerService ;

    public List<MyUser> AllUser(){
        return myUserRepository.findAll();
    }
    public void AddCustomer(CustomerDTO customerDTO){
        MyUser user = myUserRepository.findMyUsersByUsername(customerDTO.getUsername());
            MyUser myUser =new MyUser();
            myUser.setPassword(customerDTO.getPassword());
            myUser.setRole(customerDTO.getRole());
            String hashedPassword=new BCryptPasswordEncoder().encode(myUser.getPassword());
            myUser.setPassword(hashedPassword);
            myUser.setUsername(customerDTO.getUsername());
            Customer customer =new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getPhone_number(), customerDTO.getEmail_address(),customerDTO.getLicense(),customerDTO.getRentdate()
            ,customerDTO.getReturndate(),customerDTO.getBalance(),customerDTO.getAge(),myUser,null,null);
            myUser.setCustomer(customer);
            myUserRepository.save(myUser);
            customer.setMyUser(myUser);
            customerRepository.save(customer);
        }

    public void  AddOwner(OwnerDTO ownerDTO){
//        MyUser user = myUserRepository.findMyUsersByUsername(ownerDTO.getUsername());

        MyUser myUser =new MyUser();

        myUser.setPassword(ownerDTO.getPassword());

        myUser.setRole(ownerDTO.getRole());

        String hashedPassword=new BCryptPasswordEncoder().encode(myUser.getPassword());

        myUser.setPassword(hashedPassword);

        myUser.setUsername(ownerDTO.getUsername());
        myUserRepository.save(myUser);

            CarOwner carOwner = new CarOwner(ownerDTO.getId(),ownerDTO.getName(),ownerDTO.getEmail(),ownerDTO.getPhone_Number(),ownerDTO.getCustomerInfo(),ownerDTO.getCarAvailabilty(),ownerDTO.getBookingRequest(),ownerDTO.getInvoice_Details(),ownerDTO.getReturn_Date(),myUser,null);
            carOwner.setMyUser(myUser);

            carOwnerRepositry.save(carOwner);

        myUser.setCarOwner(carOwner);
            myUserRepository.save(myUser);

        }


    public void delete_user(Integer user_id){
        MyUser myUser =myUserRepository.findMyUsersById(user_id);
        if(myUser==null){
            throw new ApiException("user id not found!!");
        }
        myUserRepository.delete(myUser);
    }

    public void update_customer(CustomerDTO customerDTO ,Integer user_id){
        Customer updatecustomer = customerRepository.findCustomerByMyUserId(user_id);
        MyUser myUser = new MyUser();
        if(updatecustomer==null){
            throw new ApiException("customer id not found!!");
        } else if (updatecustomer.getMyUser().getId()!=user_id) {
            throw new ApiException("Sorry , You do not have the authority to update this customer!");
        }
        myUser.setRole(customerDTO.getRole());
        myUser.setPassword(customerDTO.getPassword());
        myUser.setId(user_id);
        myUser.setUsername(updatecustomer.getMyUser().getUsername());

        myUserRepository.save(myUser);
        Customer customer =new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getPhone_number(), customerDTO.getEmail_address(),customerDTO.getLicense(),customerDTO.getRentdate()
                ,customerDTO.getReturndate(),customerDTO.getBalance(),customerDTO.getAge(),myUser,null,null);
        customerService.UpdateCustomer(user_id,customer);
    }



}
