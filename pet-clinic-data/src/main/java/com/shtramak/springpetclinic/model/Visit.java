package com.shtramak.springpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Visit extends BaseEntity {
    private LocalDate date;
    private String description;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
