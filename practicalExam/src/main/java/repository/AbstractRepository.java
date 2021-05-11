package repository;

import domain.Identifiable;

import java.util.*;

public abstract class AbstractRepository <ID, T extends Identifiable<ID>> implements Repository<ID, T>{
    protected Map<ID,T> M;

    public AbstractRepository(){
        M=new HashMap<>();
    }

    public void add(T elem) {
        if (M.containsKey(elem.getID())) {
            throw new RuntimeException("Element already exists");
        } else {
            M.put(elem.getID(), elem);
        }
    }

    public T findByID(ID id){
        if(M.containsKey(id))
            return M.get(id);
        else
            return null;
    }

    public Iterable <T> findAll(){
        return M.values();
    }

    public Collection<T> getAll() {return M.values();}


}