package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.service.PetService;
import com.shtramak.springpetclinic.service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@ExtendWith(MockitoExtension.class)
class OwnerServiceMapTest extends AbstractMapServiceTest<OwnerServiceMap, Owner, Long> {

    @Mock
    PetService petService;
    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    OwnerServiceMap ownerServiceMap;

    @BeforeEach
    void setUp() {
        setUpMapService();
        setUpInstanceSupplier();
    }

    @Override
    void setUpMapService() {
        abstractMapService = ownerServiceMap;
        abstractMapService.map = abstractServiceMap;
    }

    @Override
    void setUpInstanceSupplier() {
        instanceSupplier = Owner::new;
    }

    @Test
    void findAllWhenElementsExistReturnsSetOfAllElements() {
        assertThat(abstractMapService.findAll(), containsInAnyOrder(abstractServiceMap.values().toArray()));
    }

    @Override
    Map<Long, Owner> generateMapWithTestData() {
        Map<Long, Owner> ownersMap = new HashMap<>();
        Owner owner1 = new Owner();
        owner1.setId(1L);
        ownersMap.put(1L, owner1);
        Owner owner2 = new Owner();
        owner1.setId(2L);
        ownersMap.put(2L, owner2);
        Owner owner3 = new Owner();
        owner1.setId(3L);
        ownersMap.put(3L, owner3);
        return ownersMap;
    }
}
