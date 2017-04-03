package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealService {
    Meal save(Meal meal, int userID);

    void delete(int id, int userID);

    Meal get(int id, int userID);

    Collection<Meal> getAll(int userID);
}