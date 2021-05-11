package repository;

import domain.CookingClass;

import java.util.List;

public interface CookingClassRepository extends Repository<Integer, CookingClass> {
    List<CookingClass> filterByDate(String date);
}
