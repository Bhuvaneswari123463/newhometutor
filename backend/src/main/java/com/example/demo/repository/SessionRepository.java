package com.example.demo.repository;

import com.example.demo.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    // Check for duplicate sessions by title and date
    boolean existsByTitleAndDate(String title, LocalDate date);

    // Fetch sessions by username
    List<Session> findByUsername(String username);

    // Fetch sessions by date
    List<Session> findByDate(LocalDate date);

    // Fetch sessions by subject
    List<Session> findBySubject(String subject);

    // Fetch sessions by username and date
    List<Session> findByUsernameAndDate(String username, LocalDate date);
}
