package com.shtramak.springpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class PetType extends BaseEntity {
    @NotBlank
    @Size(min = 2)
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
