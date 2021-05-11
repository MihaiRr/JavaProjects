package repository;

import domain.CookingClass;
import domain.Subscription;

import java.util.List;

public interface SubscriptionRepository extends Repository<Integer, Subscription> {
    List<Subscription> filterByClass(CookingClass cookingClass);

}
