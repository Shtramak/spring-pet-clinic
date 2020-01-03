package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.model.Pet;
import com.shtramak.springpetclinic.model.PetType;
import com.shtramak.springpetclinic.service.OwnerService;
import com.shtramak.springpetclinic.service.PetService;
import com.shtramak.springpetclinic.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default","map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Optional<Owner> findByLastName(String lastName) {
        return map.values()
                .stream()
                .filter(owner -> owner.getLastName().equals(lastName))
                .findAny();
    }

    @Override
    public Owner save(Owner owner) {
        Objects.requireNonNull(owner);
        Set<Pet> pets = owner.getPets();
        if (pets != null) {
            pets.forEach(this::saveRelatedEntities);
        }
        return super.save(owner);
    }

    private void saveRelatedEntities(Pet pet) {
        saveOwnersPetTypes(pet.getPetType());
        saveOwnersPet(pet);
    }

    private void saveOwnersPet(Pet pet) {
        if (pet.getId() == null) {
            Pet savedPet = petService.save(pet);
            pet.setId(savedPet.getId());
        }
    }

    private void saveOwnersPetTypes(PetType petType) {
        if (petType == null) {
            throw new RuntimeException("Pet must have a pet type");
        }
        if (petType.getId() == null) {
            PetType savedPetType = petTypeService.save(petType);
            petType.setId(savedPetType.getId());
        }
    }
}
