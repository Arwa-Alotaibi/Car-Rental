package com.example.carrental.Service_Test;


import com.example.carrental.Model.Booking_Order;
import com.example.carrental.Model.Customer;
import com.example.carrental.Model.MyUser;
import com.example.carrental.Repository.Booking_OrderRepository;
import com.example.carrental.Repository.MyUserRepository;
import com.example.carrental.Service.Booking_OrderService;
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
public class booking_orderservice_Test {
    @InjectMocks
    Booking_OrderService bookingOrderService;

    @Mock
    Booking_OrderRepository bookingOrderRepository;
    @Mock
    MyUserRepository myUserRepository;

    Customer customer;

    MyUser myUser;

    Booking_Order bookingOrder, bookingOrder1;

    List<Booking_Order> bookingOrders;


    @BeforeEach
    void setUp() {
        customer = new Customer(null,"arwa",5555,"a@gmail.com","11111",null,null,55.6,16,myUser,null,null);
        myUser = new MyUser(null, "shaima", "Customer", "123",customer , null);
        bookingOrder = new Booking_Order(null, "full", 4, 55.4, 33.4, myUser.getCustomer(), null);
        bookingOrder1 = new Booking_Order(null, "full", 4, 55.4, 33.4, myUser.getCustomer(), null);
        bookingOrders = new ArrayList<>();
        bookingOrders.add(bookingOrder);
        bookingOrders.add(bookingOrder1);
    }

    @Test
    public void getAllbooking() {
        when(bookingOrderRepository.findAll()).thenReturn(bookingOrders);
        List<Booking_Order> bookingOrderList = bookingOrderService.Allbooking();
        Assertions.assertEquals(2, bookingOrderList.size());
        verify(bookingOrderRepository, times(1)).findAll();
    }


    @Test
    public void addbooking(){
        bookingOrderService.AddBooking(bookingOrder);
        verify(bookingOrderRepository,times(1)).save(bookingOrder1);
    }



    }

