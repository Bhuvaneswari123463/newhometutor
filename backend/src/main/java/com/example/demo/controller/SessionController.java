package com.example.demo.controller;

import com.example.demo.entity.Session;
import com.example.demo.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from the frontend
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    public ResponseEntity<?> createSession(@RequestBody Session session) {
        // Check for duplicate session
        boolean exists = sessionRepository.existsByTitleAndDate(session.getTitle(), session.getDate());
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Session already exists");
        }

        sessionRepository.save(session);
        return ResponseEntity.ok("Session created successfully");
    }

    @GetMapping
    public List<Session> getAllSessions() {
        System.out.println("GET /api/sessions endpoint called");
        return sessionRepository.findAll();
    }

    @GetMapping("/user/{username}")
    public List<Session> getSessionsByUsername(@PathVariable String username) {
        return sessionRepository.findByUsername(username);
    }

    @GetMapping("/date/{date}")
    public List<Session> getSessionsByDate(@PathVariable LocalDate date) {
        return sessionRepository.findByDate(date);
    }

    @GetMapping("/subject/{subject}")
    public List<Session> getSessionsBySubject(@PathVariable String subject) {
        return sessionRepository.findBySubject(subject);
    }

    @GetMapping("/user/{username}/date/{date}")
    public List<Session> getSessionsByUsernameAndDate(@PathVariable String username, @PathVariable LocalDate date) {
        return sessionRepository.findByUsernameAndDate(username, date);
    }
}
