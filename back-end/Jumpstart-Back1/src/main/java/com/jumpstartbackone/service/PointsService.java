package com.jumpstartbackone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpstartbackone.entity.Points;
import com.jumpstartbackone.entity.User;
import com.jumpstartbackone.repository.PointsRepository;

@Service
public class PointsService {

	@Autowired
	PointsRepository pointsRepository;
	
	public Double getUserPoints(User user) {
        Optional<Points> pointsOptional = pointsRepository.findByUser(user);
        return pointsOptional.map(Points::getPoints).orElse(0.0);
    }

    public void addPointsToUser(User user, Double pointsToAdd) {
        Double currentPoints = getUserPoints(user);
        Double newPoints = currentPoints + pointsToAdd;

        Points points = pointsRepository.findByUser(user)
            .orElse(new Points());
        points.setUser(user);
        points.setPoints(newPoints);

        pointsRepository.save(points);
    }
}
