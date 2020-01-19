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
    @NotBlank(message = "This field is mandatory. Please enter first name")
    @Size(min = 2, message = "Minimum length is 2 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "This field is mandatory. Please enter last name")
    @Size(min = 2, message = "Minimum length is 2 characters")
    @Column(name = "last_name")
    private String lastName;

    private String address;
    private String city;
    private String telephone;
}
