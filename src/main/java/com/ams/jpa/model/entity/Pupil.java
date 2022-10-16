package com.ams.jpa.model.entity;

import com.ams.jpa.model.enumeration.PersonStatus;
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
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.hash;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Entity(name = "Pupil")
@Table(name = "pupil")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Cache(usage = READ_WRITE)
public class Pupil extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    private String firstname;

    @Column(name = "last_name", nullable = false)
    private String lastname;

    @Column(name = "status", nullable = false)
    @Enumerated(STRING)
    @Builder.Default
    private PersonStatus status = PersonStatus.ACTIVE;

    @Builder.Default
    @OneToMany(mappedBy = "pupil", cascade = ALL, orphanRemoval = true, fetch = LAZY)
    private List<Grade> grades = new ArrayList<>();

    @ManyToOne(cascade = ALL, fetch = LAZY, optional = false)
    @JoinColumn(name = "headteacher_id", nullable = false)
    private HeadTeacher headTeacher;

    @Override
    public int hashCode() {
        return hash(this.id);
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Pupil p && this.id.equals(p.id);
    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("id", this.id).toString();
    }
}