package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {



    @PersistenceContext
    private EntityManager  em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if(meal.isNew()){
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }

    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal current = em.getReference(Meal.class , id);
        int chekOwnerID = current.getUser().getId();

        if(userId == chekOwnerID) return current;
        else return ValidationUtil.checkNotFound(current, String.valueOf("Meal id = " + userId));

    }

//    @Override
//    public List<Meal> getAll(int userId) {
//        return (List<Meal>) em.createQuery("SELECT m FROM Meal m WHERE m.user.id = :userId")
//                .setParameter("userId" , userId)
//                .getResultList();
//    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.GET_ALL, Meal.class).setParameter(1,userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.GET_BETWEEN , Meal.class).setParameter(1, userId).setParameter(2, startDate).setParameter(3 , endDate).getResultList();
    }
}