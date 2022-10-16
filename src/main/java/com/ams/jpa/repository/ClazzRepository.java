package com.ams.jpa.repository;

import com.ams.jpa.model.entity.Clazz;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.Optional;

import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;

public interface ClazzRepository extends JpaRepository<Clazz, String> {
    @QueryHints(@QueryHint(name = HINT_CACHEABLE, value = "true"))
    @NonNull Optional<Clazz> findByName(@NonNull String name);
}