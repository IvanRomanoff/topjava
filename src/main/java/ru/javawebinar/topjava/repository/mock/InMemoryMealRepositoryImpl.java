package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_LIST;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach((meal) -> save(meal, 1));
    }


    @Override
    public Meal save(Meal meal, int userID) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserID(userID);
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id, int userID) {
        if (repository.get(id).getUserID() == userID) {
            repository.remove(id);
        }
    }

    @Override
    public Meal get(int id, int userID) {
        Meal chekedMeal = repository.get(id);
        if (chekedMeal.getUserID() == userID) {
            return chekedMeal;
        } else {
            return null;
        }
    }

    @Override
    public List<Meal> getAll(int userID) {
        List<Meal> meallist = repository.values().stream()
                            .filter((val) -> val.getUserID() == userID)
                            .collect(Collectors.toList());

         meallist.sort((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));
        return meallist.isEmpty()? Collections.EMPTY_LIST : meallist;
    }
}