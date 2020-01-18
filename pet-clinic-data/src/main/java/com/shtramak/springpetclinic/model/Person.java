package com.shtramak.springpetclinic.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@MappedSuperclass
@RequiredArgsConstructor
public class Person extends BaseEntity {
    @NotBlank
    @Size(min = 2)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(min = 2)
    @Column(name = "last_name")
    private String lastName;

    private String address;
    private String city;
    private String telephone;
}
