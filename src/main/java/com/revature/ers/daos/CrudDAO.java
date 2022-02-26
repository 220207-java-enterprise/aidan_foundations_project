package com.revature.ers.daos;

import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> {
    // POST
    void save(T newModel);

    // GET (1)
    T getById(String id);

    // GET (All)
    List<T> getAll();

    // PUT
    void update(T updateModel);

    // DELETE
    void deleteById(String id);

}
