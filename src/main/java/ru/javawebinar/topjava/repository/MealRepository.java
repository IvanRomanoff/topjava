package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    Meal save(Meal meal, int userID);

    void delete(int id, int userID);

    Meal get(int id, int userID);

    List getAll(int userID);
}
