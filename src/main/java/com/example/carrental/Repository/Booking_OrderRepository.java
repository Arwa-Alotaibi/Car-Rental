package com.example.carrental.Repository;

import com.example.carrental.Model.Booking_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Booking_OrderRepository extends JpaRepository<Booking_Order,Integer> {
    Booking_Order findBooking_OrderById(Integer id);
}
