package com.example.demo.controller;

import com.example.demo.entity.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from React frontend
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        try {
            bookingRepository.save(booking);
            return ResponseEntity.ok("Booking created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create booking");
        }
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkIfBookingExists(
            @RequestParam Long userId,
            @RequestParam Long sessionId) {
        boolean exists = bookingRepository.existsByUserIdAndSessionId(userId, sessionId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long bookingId) {
        try {
            bookingRepository.deleteById(bookingId);
            return ResponseEntity.ok("Booking deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete booking");
        }
    }
}
