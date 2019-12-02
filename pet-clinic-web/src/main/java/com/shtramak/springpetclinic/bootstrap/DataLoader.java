package com.shtramak.springpetclinic.bootstrap;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.model.Pet;
import com.shtramak.springpetclinic.model.PetType;
import com.shtramak.springpetclinic.model.Vet;
import com.shtramak.springpetclinic.service.OwnerService;
import com.shtramak.springpetclinic.service.PetTypeService;
import com.shtramak.springpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final VetService vetService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public DataLoader(VetService vetService, OwnerService ownerService, PetTypeService petTypeService) {
        this.vetService = vetService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loaded PetTypes...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Oleg");
        owner1.setLastName("Skrypka");
        owner1.setAddress("123 Derebasovska");
        owner1.setCity("Odessa");
        owner1.setTelephone("23476511");
        Pet dogPet = new Pet();
        dogPet.setPetType(savedDogPetType);
        dogPet.setName("Snoop Dog");
        owner1.getPets().add(dogPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Oleg");
        owner2.setLastName("Vinnyk");
        owner2.setAddress("123 Silska");
        owner2.setCity("Berdychiv");
        owner2.setTelephone("987654231");
        Pet catPet = new Pet();
        catPet.setPetType(cat);
        catPet.setName("Kitty");
        owner2.getPets().add(catPet);
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sasha");
        vet1.setLastName("Kasha");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("John");
        vet2.setLastName("Doe");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
