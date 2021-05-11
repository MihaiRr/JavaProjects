package repository;

import java.util.Collection;

public interface Repository<ID,T>{
    void add(T elem);
    void delete(T elem);
    void update(T elem, ID id);
    T findByID(ID id);
    Iterable <T> findAll();
    Collection<T> getAll();
}