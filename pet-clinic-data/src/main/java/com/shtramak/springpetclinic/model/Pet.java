package com.shtramak.springpetclinic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Pet extends BaseEntity {
    private String name;
    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private Set<Visit> visits = new HashSet<>();
}
