package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class VetServiceMapTest extends AbstractMapServiceTest<VetServiceMap, Vet, Long> {

    private VetServiceMap vetServiceMap;

    @BeforeEach
    void setUp() {
        vetServiceMap = new VetServiceMap();
        setUpMapService();
        setUpInstanceSupplier();
    }

    @Override
    Map<Long, Vet> generateMapWithTestData() {
        return LongStream.rangeClosed(0, 3)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), id -> {
                            Vet vet = new Vet();
                            vet.setId(id);
                            return vet;
                        })
                );
    }

    @Override
    void setUpMapService() {
        abstractMapService = vetServiceMap;
        vetServiceMap.map = abstractServiceMap;
    }

    @Override
    void setUpInstanceSupplier() {
        instanceSupplier = Vet::new;
    }
}
