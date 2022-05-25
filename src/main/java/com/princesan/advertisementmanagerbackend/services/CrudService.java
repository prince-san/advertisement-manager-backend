package com.princesan.advertisementmanagerbackend.services;

import java.util.List;

public interface CrudService<T> {

    T create(T t);

    List<T> getAll();

    T get(Long id);

    T update(T t);

    void delete(Long id);
}
