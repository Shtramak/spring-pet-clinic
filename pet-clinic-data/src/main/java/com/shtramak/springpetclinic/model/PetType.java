package com.shtramak.springpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class PetType extends BaseEntity {
    private String name;
}
