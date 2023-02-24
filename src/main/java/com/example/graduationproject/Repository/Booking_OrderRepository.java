package com.example.graduationproject.Repository;

import com.example.graduationproject.Model.Booking_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Booking_OrderRepository extends JpaRepository<Booking_Order,Integer> {
    Booking_Order findBooking_OrderById(Integer id);
}
