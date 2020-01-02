package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.BaseEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

abstract class AbstractMapServiceTest<T extends AbstractMapService, E extends BaseEntity, ID extends Long> {

    T abstractMapService;
    Map<ID, E> abstractServiceMap;
    Supplier<E> instanceSupplier; // todo provide some Supplier for instance

    {
        abstractServiceMap = generateMapWithTestData();
    }

    abstract Map<ID, E> generateMapWithTestData();

    abstract void setUpMapService();

    abstract void setUpInstanceSupplier();

    @Test
    void findByIdWhenElementExistReturnsOptionalWithElement() {
        ID existedId = abstractServiceMap.entrySet().iterator().next().getKey();
        E existedElement = abstractServiceMap.get(existedId);
        Optional result = abstractMapService.findById(existedId);
        assertEquals(existedElement, result.get());
    }

    @Test
    void findByIdWhenElementExistReturnsOptionalEmpty() {
        Long notExistedId = -1L;
        Optional result = abstractMapService.findById(notExistedId);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void findAllWhenElementsNotExistReturnsEmptySet() {
        abstractMapService.map = new HashMap<>();
        assertEquals(Collections.emptySet(), abstractMapService.findAll());
    }


    @Test
    void saveWhenNewInstanceAddItToServiceMap() {
        E entity = instanceSupplier.get();
        BaseEntity savedEntity = abstractMapService.save(entity);
        E entityFromMap = abstractServiceMap.get(savedEntity.getId());
        assertEquals(savedEntity, entityFromMap);
    }

    @Test
    void deleteWhenElementExistsDeleteElementFromServiceMap() {
        E entity = abstractServiceMap.entrySet().iterator().next().getValue();
        abstractMapService.delete(entity);
        assertFalse(abstractServiceMap.containsValue(entity));
    }

    @Test
    void deleteByIdWhenIdExistDeleteElementByIdFromServiceMap() {
        Long existingKey = abstractServiceMap.entrySet().iterator().next().getKey();
        abstractMapService.deleteById(existingKey);
        assertFalse(abstractServiceMap.containsKey(existingKey));
    }
}
