package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.BaseEntity;
import com.shtramak.springpetclinic.service.CrudService;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> implements CrudService<T, ID> {
    private AtomicLong idGenerator = new AtomicLong(0);

    protected Map<Long, T> map;

    @Override
    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.of(map.get(id));
    }

    @Override
    public T save(T object) {
        Long id = idGenerator.incrementAndGet();
        object.setId(id);
        map.put(id, object);
        return object;
    }

    @Override
    public void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    @Override
    public void deleteById(ID id) {
        map.remove(id);
    }
}
