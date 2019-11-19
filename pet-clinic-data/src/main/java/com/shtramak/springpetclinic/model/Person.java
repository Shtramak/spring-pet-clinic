package com.shtramak.springpetclinic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person extends BaseEntity {
    private Long id;
    private String firstName;
    private String lastName;
}
