package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 28.03.2017.
 */
public class DaoMealMemoryImplementation implements DaoMeal {
    List<Meal> mealsList = new ArrayList<>();

    @Override
    public void addMeal(Meal meal) {
        try {
            Meal old = mealsList.get(meal.getId());
        } catch (IndexOutOfBoundsException e) {
            mealsList.add(meal);
        }
    }

    @Override
    public Meal getMealNyId(int num) {
        return mealsList.get(num);
    }

    @Override
    public void updateMeal(Meal meal, int id) {
        try {
            Meal old = mealsList.get(id);
        } catch (IndexOutOfBoundsException e) {
            addMeal(meal);
        }
    }

    @Override
    public void removeMeal(int id) {
        this.mealsList.remove(id);
    }

    @Override
    public void createNewMeals(List<Meal> list) {
        this.mealsList = list;
    }

    @Override
    public List<Meal> getAllrecord() {
        return mealsList;
    }
}
