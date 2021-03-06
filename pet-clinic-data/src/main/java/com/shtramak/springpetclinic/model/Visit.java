package com.shtramak.springpetclinic.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Visit extends BaseEntity {
    @Future(message = "Visit must be in future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
