package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.dao.DaoMeal;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Администратор on 29.03.2017.
 */
public class MealService {
    private DaoMeal daoMeal;

    public MealService(DaoMeal daoMeal) {
        this.daoMeal = daoMeal;
    }


    public void addMeal(Meal meal) {
        this.daoMeal.addMeal(meal);
    }
    public List<Meal> getMeallist() {
        return this.daoMeal.getAllrecord();

    }
//    public Meal getMealNyId(int num) {
//
//    }
//
//    public void updateMeal(Meal meal, int id) {
//
//    }
//
//    public void removeMeal(int id) {
//
//    }
//
//    public void createNewMeals(List<Meal> list) {
//
//    }

}
