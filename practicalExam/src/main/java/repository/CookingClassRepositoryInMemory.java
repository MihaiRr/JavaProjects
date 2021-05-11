package repository;

import domain.CookingClass;

import java.util.List;
import java.util.stream.Collectors;

public class CookingClassRepositoryInMemory extends AbstractRepository<Integer, CookingClass> implements CookingClassRepository {

    @Override
    public List<CookingClass> filterByDate(String date) {
        return this.getAll().stream().filter(cookingClass -> cookingClass.getDate().equals(date)).collect(Collectors.toList());
    }
}
