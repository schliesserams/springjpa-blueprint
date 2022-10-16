package com.ams.jpa.repository;

import com.ams.jpa.model.entity.HeadTeacher;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeadTeacherRepository extends JpaRepository<HeadTeacher, String> {
    @NonNull Optional<HeadTeacher> findByName(@NonNull String name);
}