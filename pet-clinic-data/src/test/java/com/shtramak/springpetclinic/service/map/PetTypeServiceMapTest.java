package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class PetTypeServiceMapTest extends AbstractMapServiceTest<PetTypeServiceMap, PetType, Long> {

    private PetTypeServiceMap petTypeService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeServiceMap();
        setUpMapService();
        setUpInstanceSupplier();
    }

    @Override
    Map<Long, PetType> generateMapWithTestData() {
        return LongStream.rangeClosed(0, 3)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), id -> {
                            PetType petType = new PetType();
                            petType.setId(id);
                            return petType;
                        })
                );
    }

    @Override
    void setUpMapService() {
        abstractMapService = petTypeService;
        petTypeService.map = abstractServiceMap;
    }

    @Override
    void setUpInstanceSupplier() {
        instanceSupplier = PetType::new;
    }

}
