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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

import static java.util.Objects.hash;
import static javax.persistence.FetchType.LAZY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Entity
@Table(name = "grade", indexes = {
    @Index(name = "unique_grade_index", columnList = "clazz_id, student_id"),
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Cache(usage = READ_WRITE)
public class Grade extends BaseEntity {
    @Column(name = "grade_value", nullable = false)
    private BigDecimal gradeValue;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "clazz_id", nullable = false)
    private Clazz clazz;

    @Override
    public int hashCode() {
        return hash(this.id);
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Grade g && this.id.equals(g.id);
    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("id", this.id).toString();
    }
}