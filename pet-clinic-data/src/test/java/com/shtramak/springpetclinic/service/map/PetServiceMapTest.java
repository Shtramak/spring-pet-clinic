package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class PetServiceMapTest extends AbstractMapServiceTest<PetServiceMap, Pet, Long> {

    private PetServiceMap petServiceMap;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();
        setUpMapService();
        setUpInstanceSupplier();
    }

    @Override
    Map<Long, Pet> generateMapWithTestData() {
        return LongStream.rangeClosed(0, 3)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), id -> {
                            Pet pet = new Pet();
                            pet.setId(id);
                            return pet;})
                );
    }

    @Override
    void setUpMapService() {
        abstractMapService = petServiceMap;
        petServiceMap.map = abstractServiceMap;
    }

    @Override
    void setUpInstanceSupplier() {
        instanceSupplier = Pet::new;
    }
}
