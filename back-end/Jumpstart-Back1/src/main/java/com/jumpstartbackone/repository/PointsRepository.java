package com.jumpstartbackone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpstartbackone.entity.Points;
import com.jumpstartbackone.entity.User;

public interface PointsRepository extends JpaRepository<Points, Long> {
    Optional<Points> findByUser(User user);
    Optional<Points> findPointsByUser(User user);
}

