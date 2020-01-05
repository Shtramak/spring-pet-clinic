package com.shtramak.springpetclinic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Owner extends Person {
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private Set<Pet> pets = new HashSet<>();

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
    }
}
