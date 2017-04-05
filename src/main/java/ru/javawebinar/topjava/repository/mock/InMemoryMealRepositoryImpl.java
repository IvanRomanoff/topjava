package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
        if (meal.getUserID() == userID) {
            repository.put(meal.getId(), meal);
        }
        return meal;
    }

    @Override
    public void delete(int id, int userID) {
        try {
            if (repository.get(id).getUserID() == userID) {
                repository.remove(id);
            }
        } catch (NullPointerException e) {
        }
    }

    @Override
    public Meal get(int id, int userID) {
        Meal chekedMeal = null;
        if (repository.containsKey(id)) {
            chekedMeal = repository.get(id);
            if (chekedMeal.getUserID() == userID) {
                return chekedMeal;
            }
        }
        return chekedMeal;
    }

    @Override
    public List<Meal> getAll(int userID) {
        List<Meal> meallist = repository.values().stream()
                .filter((val) -> val.getUserID() == userID)
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());

        return meallist.isEmpty() ? Collections.EMPTY_LIST : meallist;
    }
}