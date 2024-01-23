package com.rossi.gym.repository;

import com.rossi.gym.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GymRepository extends JpaRepository<Gym, UUID> {
}
