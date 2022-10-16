package com.ams.jpa.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static java.util.Objects.hash;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Entity
@Table(name = "clazz")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Cache(usage = READ_WRITE)
public class Clazz extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Override
    public int hashCode() {
        return hash(this.id);
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Clazz s && this.id.equals(s.id);
    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("id", this.id).toString();
    }
}