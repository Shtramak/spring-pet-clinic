package com.shtramak.springpetclinic.bootstrap;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.model.Pet;
import com.shtramak.springpetclinic.model.PetType;
import com.shtramak.springpetclinic.model.Speciality;
import com.shtramak.springpetclinic.model.Vet;
import com.shtramak.springpetclinic.model.Visit;
import com.shtramak.springpetclinic.service.OwnerService;
import com.shtramak.springpetclinic.service.PetTypeService;
import com.shtramak.springpetclinic.service.SpecialityService;
import com.shtramak.springpetclinic.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final VetService vetService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Pes");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Kit");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Owner owner1 = new Owner();
        owner1.setFirstName("Mykola");
        owner1.setLastName("Panasovych");
        owner1.setAddress("123, Global Logic");
        owner1.setCity("Kyiv");
        owner1.setTelephone("+380501231312");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Tuzik");
        owner1.addPet(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Maria");
        owner2.setLastName("Ivanivna");
        owner2.setAddress("456, Hrinchenka street");
        owner2.setCity("Kyiv");
        owner2.setTelephone("+380674564546");

        Pet fionasCat = new Pet();
        fionasCat.setName("Murka");
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedCatPetType);
        owner2.addPet(fionasCat);

        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.now().plusDays(10));
        catVisit.setDescription("Shmarky");
        fionasCat.addVisit(catVisit);
        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Andrii");
        vet1.setLastName("Yarmolenko");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Vasyl");
        vet2.setLastName("Virastiuk");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
