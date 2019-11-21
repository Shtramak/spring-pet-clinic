package com.shtramak.springpetclinic.bootstrap;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.model.Vet;
import com.shtramak.springpetclinic.service.OwnerService;
import com.shtramak.springpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final VetService vetService;
    private final OwnerService ownerService;

    public DataLoader(VetService vetService, OwnerService ownerService) {
        this.vetService = vetService;
        this.ownerService = ownerService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Oleg");
        owner1.setLastName("Skrypka");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Oleg");
        owner2.setLastName("Vinnyk");
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
