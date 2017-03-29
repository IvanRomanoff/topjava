package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Администратор on 28.03.2017.
 */
public interface DaoMeal {

    public void addMeal(Meal meal);

    public Meal getMealNyId(int num);

    public void updateMeal(Meal meal, int id);

    public void removeMeal(int id);

    public void createNewMeals(List<Meal> list);

    public List<Meal> getAllrecord();
}