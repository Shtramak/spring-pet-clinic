package com.shtramak.springpetclinic.repository;

import com.shtramak.springpetclinic.model.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> findByLastName(String lastName);

    @Query("select o from Owner o left join fetch o.pets as p where p.name = :name")
    List<Owner> findAllOwnersByPetName(@Param("name") String name);
}
