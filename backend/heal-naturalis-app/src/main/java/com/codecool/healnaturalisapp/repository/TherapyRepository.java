package com.codecool.healnaturalisapp.repository;

import com.codecool.healnaturalisapp.model.Therapy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapyRepository extends JpaRepository<Therapy, Long> {
}
