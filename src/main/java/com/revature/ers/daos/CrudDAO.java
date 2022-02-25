package com.revature.ers.daos;

import java.util.ArrayList;

public interface CrudDAO<T> {
    // POST
    void save(T newModel);

    // GET (1)
    T getById(String id);

    // GET (All)
    ArrayList<T> getAll();

    // PUT
    void update(T updateModel);

    // DELETE
    void deleteById(String id);

}
