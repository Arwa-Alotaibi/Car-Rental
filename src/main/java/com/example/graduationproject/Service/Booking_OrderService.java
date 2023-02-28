package com.example.graduationproject.Service;

import com.example.graduationproject.Exception.ApiException;
import com.example.graduationproject.Model.*;
import com.example.graduationproject.Repository.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Booking_OrderService {
    private Booking_OrderRepository bookingOrderRepository;

    private CustomerRepository customerRepository;
    private CarRepository carRepository;
    private IsReservedRepositry isReservedRepositry;



    public Booking_OrderService(Booking_OrderRepository bookingOrderRepository, CustomerRepository customerRepository,CarRepository carRepository){
        this.bookingOrderRepository=bookingOrderRepository;
        this.customerRepository=customerRepository;
        this.carRepository=carRepository;
    }

    // admin - users
    public void AddBooking(Booking_Order bookingOrder){
        bookingOrderRepository.save(bookingOrder);
    }

    //  admin - users
    public void updatebooking(Integer booking_id,Booking_Order bookingOrder,MyUser user){
        Booking_Order update_booking = bookingOrderRepository.findBooking_OrderById(booking_id);
        if(update_booking==null){
            throw new ApiException("booking id not found!!");
        }
        else if (user.getRole().equals("Customer")) {
            if (update_booking.getCustomer().getMyUser().getId()!= user.getId()){
                throw new ApiException("you do not have auth");
            }
        }
        update_booking.setTotal_price(bookingOrder.getTotal_price());
        update_booking.setTotal_days(bookingOrder.getTotal_days());
        update_booking.setInsurance_type(bookingOrder.getInsurance_type());
        update_booking.setInsurance_price(bookingOrder.getInsurance_price());
        bookingOrderRepository.save(update_booking);
    }

    //  admin - users
    public List<Booking_Order>Allbooking(){
        return bookingOrderRepository.findAll();
    }

    //admin - users
    public void Delete_Booking(Integer booking_id,MyUser user){
        Booking_Order delete_booking = bookingOrderRepository.findBooking_OrderById(booking_id);
        if(delete_booking==null){
            throw new ApiException("booking id not found!!");
        } else if (user.getRole().equals("Customer")) {
            if (delete_booking.getCustomer().getMyUser().getId()!= user.getId()){
                throw new ApiException("you do not have auth");
            }
        }
        bookingOrderRepository.delete(delete_booking);
    }
    // admin 
    public void Assign(Integer booking_id , Integer car_id ){
        Booking_Order bookingOrder = bookingOrderRepository.findBooking_OrderById(booking_id);
        Car car = carRepository.findCarById(car_id);
        if(bookingOrder==null ||  car==null){
            throw new ApiException(" booking id or car id not found");
        }
        bookingOrder.setCar(car);
        bookingOrderRepository.save(bookingOrder);
    }
    // Admin
    //Insurance calculation according to the car class
    public void Insurance_calculation_carclass(Integer booking_id, Integer car_id){
        Booking_Order bookingOrder = bookingOrderRepository.findBooking_OrderById(booking_id);
        Car car = carRepository.findCarById(car_id);
        if(bookingOrder==null ||  car==null){
            throw new ApiException(" booking id or car id not found");
        }
        if(car.getCar_class().equals("economic")){
            bookingOrder.setInsurance_price(bookingOrder.getInsurance_price()+1000);
        } else if (car.getCar_class().equals("classic")) {
            bookingOrder.setInsurance_price(bookingOrder.getInsurance_price()+500);
        }
        bookingOrder.setTotal_price(bookingOrder.getInsurance_price()+bookingOrder.getTotal_price());
        bookingOrderRepository.save(bookingOrder);

    }

    // customer
    public void Car_rental(Integer user_id , Integer booking_id,Integer car_id){
        Customer customer = customerRepository.findCustomersById(user_id);
        Booking_Order bookingOrder = bookingOrderRepository.findBooking_OrderById(booking_id);
        Car car = carRepository.findCarById(car_id);
        boolean check_black_list=black_list(user_id ,booking_id,car_id);
        if (!check_black_list){
            throw new ApiException("this customer black listed");
        }
         if (customer.getMyUser().getId()!=user_id) {
            throw new ApiException("you do not have the authority to rent car !");
        }
       else if(customer==null || bookingOrder==null ||  car==null){
            throw new ApiException("customer id not found or booking id or car id not found");
        }
        else if (!customer.getViolations_list().isEmpty()){
            throw new ApiException("You can't book a car, pay your violations");
        } else if (customer.getAge()<16) {
            throw new ApiException("You must be over 16 years old");
        } else if (bookingOrder.getInsurance_type().equals("Third party insurance")) {
            bookingOrder.setInsurance_price(bookingOrder.getInsurance_price()+500);
        } else if (bookingOrder.getInsurance_type().equals("full insurance")) {
            bookingOrder.setInsurance_price(bookingOrder.getInsurance_price()+100);
        }
        bookingOrder.setTotal_price(car.getPrice()*bookingOrder.getTotal_days() + bookingOrder.getInsurance_price());
        if(bookingOrder.getTotal_price()>customer.getBalance()){
            throw new ApiException("You can't book a car, The amount in your balance is less than the Total_price");
        }
        double All_total = customer.getBalance()-bookingOrder.getTotal_price();
        customer.setBalance(All_total);
        bookingOrder.setCustomer(customer);
        //bookingOrder.setCar(car);
        bookingOrderRepository.save(bookingOrder);
        customerRepository.save(customer);
    }


    // customer
    public void cancel_reservation(Integer booking_id, Integer user_id){
        Customer customer = customerRepository.findCustomersById(user_id);
        Booking_Order bookingOrder = bookingOrderRepository.findBooking_OrderById(booking_id);
        if (customer.getMyUser().getId()!=user_id) {
            throw new ApiException("you do not have the authority to rent car !");
        }
       else if(customer==null || bookingOrder==null){
            throw new ApiException("customer id not found or booking id ");
        }
        customer.getBookingOrderList().remove(bookingOrder);
        bookingOrder.getCustomer().getBookingOrderList().remove(customer);
        bookingOrderRepository.save(bookingOrder);
        customerRepository.save(customer);
    }

    // customer
    public void IsReserved(Integer customer_id , Integer booking_id, Integer car_id, Date reserve_date){
        List<IsReserved> check_date =isReservedRepositry.findAllByCar_Id(car_id);
        Customer customer = customerRepository.findCustomersById(customer_id);
        if (customer.getMyUser().getId()!=customer_id) {
            throw new ApiException("you do not have the authority to rent car !");
        }
        for (IsReserved check:check_date) {
            if(reserve_date==check.getReserved_Date()){
                throw new ApiException("this date is already booked");
            }

        }
        Car_rental(customer_id,booking_id,car_id);
    }
// customer
    public void Payment_method(String Customer_Choice,int period,Integer booking_id,Integer customer_id){
        Booking_Order bookingOrder = bookingOrderRepository.findBooking_OrderById(booking_id);
        Customer customer = customerRepository.findCustomersById(customer_id);
        if (customer.getMyUser().getId()!=customer_id) {
            throw new ApiException("you do not have the authority to rent car !");
        }
        double total = bookingOrder.getTotal_price();

        if(Customer_Choice=="Instalment") {
            total = total / period;
            System.out.println("your instalment payment for :" + period + " months will be :" + total + " every " + period + " months");
        }
        else
            System.out.println("your total payment is: "+total);

    }
    //Admin , owner  customer
    public boolean black_list(Integer customer_id , Integer booking_id,Integer car_id){
        Customer customer = customerRepository.findCustomersById(customer_id);
        Booking_Order bookingOrder = bookingOrderRepository.findBooking_OrderById(booking_id);
        Car car = carRepository.findCarById(car_id);
        if(customer==null ||  bookingOrder==null  ||  car==null){
            throw new ApiException("customer id not found or booking id or car id not found");
        }
        if(customer.getViolations_list().size()>=3){
            System.out.println("you have to pay your violation");
            return false;
        }
        return true;

    }
}
