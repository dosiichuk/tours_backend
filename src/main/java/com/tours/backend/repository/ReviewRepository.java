package com.tours.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tours.backend.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    
}
