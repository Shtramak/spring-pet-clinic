package com.shtramak.springpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Vet extends Person {
    private String address;
    private String city;
    private String telephone;
    private Set<Speciality> specialities;
}
