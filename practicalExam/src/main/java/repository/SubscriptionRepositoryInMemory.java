package repository;

import domain.CookingClass;
import domain.Subscription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionRepositoryInMemory extends AbstractRepository<Integer, Subscription> implements SubscriptionRepository {

    @Override
    public List<Subscription> filterByClass(CookingClass cookingClass) {
        return this.getAll().stream().filter(subscription -> subscription.getCookingClass().getID()==cookingClass.getID()).collect(Collectors.toList());
    }
}
