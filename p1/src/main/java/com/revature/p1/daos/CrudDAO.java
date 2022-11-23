package com.revature.p1.daos;

import java.util.List;

public interface CrudDAO <T> {
    void save();
    void delete();
    void update();
    List<T> findAll();
    T findById();

}
