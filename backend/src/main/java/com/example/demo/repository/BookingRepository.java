package com.example.demo.repository;

import com.example.demo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Check if a booking exists for a specific user and session
    boolean existsByUserIdAndSessionId(Long userId, Long sessionId);

    // Fetch bookings by userId
    List<Booking> findByUserId(Long userId);
}
