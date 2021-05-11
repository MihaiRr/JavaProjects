package controller;

public interface ControllerInterface <T> {
    void add(T elem);
    void delete(T elem);
    void update(T elem);
    Iterable<T> printAll();

}
