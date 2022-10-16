package com.ams.jpa.repository;

import com.ams.jpa.model.entity.Pupil;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PupilRepository extends JpaRepository<Pupil, String> {
    @NonNull Optional<Pupil> findByLastname(@NonNull String lastname);
}