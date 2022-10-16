package com.ams.jpa.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    protected String id;

    @Column(name = "create_date", nullable = false, updatable = false)
    protected ZonedDateTime createDate;

    @Column(name = "modify_date", nullable = false)
    protected ZonedDateTime modifyDate;

    @PreUpdate
    protected void onPreUpdate() {
        modifyDate = ZonedDateTime.now(UTC);
    }

    @PrePersist
    protected void onPrePersist() {
        if (createDate == null) {
            createDate = ZonedDateTime.now(UTC);
        }
        modifyDate = createDate;
    }
}
