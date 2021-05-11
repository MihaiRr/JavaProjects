package service;

import domain.CookingClass;
import domain.Subscription;
import repository.CookingClassRepository;
import repository.RepositoryException;
import repository.SubscriptionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private final CookingClassRepository cookingClassRepository;
    private final SubscriptionRepository subscriptionRepository;

    public Service(CookingClassRepository cookingClassRepository, SubscriptionRepository subscriptionRepository){
        this.cookingClassRepository=cookingClassRepository;
        this.subscriptionRepository=subscriptionRepository;
    }

    public int addCookingClassController(String name, String type, int price, int maxPlaces, String date){
        try{
            CookingClass cookingClass=new CookingClass(name, type,price,maxPlaces,date);
            cookingClassRepository.add(cookingClass);
            return cookingClass.getID();

        }catch (RepositoryException rEx){
            throw new ServiceException(rEx);

        }

    }

    public int addSubscriptionController(String personName, String phoneNumber, String address, CookingClass cookingClass){
        try{
            Subscription s=new Subscription(personName, phoneNumber, address, cookingClass);
            subscriptionRepository.add(s);
            return s.getID();

        }catch (RepositoryException rEx){
            throw new ServiceException(rEx);

        }

    }

    public List<CookingClass> getAllCooking(){return cookingClassRepository.getAll().stream().collect(Collectors.toList());}
    public List<Subscription> getAllSubscription(){return subscriptionRepository.getAll().stream().collect(Collectors.toList());}
    public List<Subscription> filterSByClass(CookingClass c) {return subscriptionRepository.filterByClass(c);}
    public List<CookingClass> filterCByDate(String date) {return cookingClassRepository.filterByDate(date);}
}
