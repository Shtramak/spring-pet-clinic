package com.shtramak.springpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Speciality extends BaseEntity {
    private String description;
}
