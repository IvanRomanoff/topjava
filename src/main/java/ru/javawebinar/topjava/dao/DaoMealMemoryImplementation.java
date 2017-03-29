package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Администратор on 28.03.2017.
 */
public class DaoMealMemoryImplementation implements DaoMeal {
    static AtomicInteger id;
    ConcurrentMap<Integer,Meal> map = new ConcurrentHashMap<>();


    @Override
    public void add(Meal meal) {
            map.put(id.incrementAndGet() , meal);
    }

    @Override
    public Meal get(int id) {
        return map.get(id);
    }

    @Override
    public void update(Meal meal) {
        map.put(meal.getId() , meal);
    }

    @Override
    public void remove(int id) {
        this.map.remove(id);
    }

    @Override
    public void factoryMethod() {
        this.map = MealsUtil.mealFactory();
         id = new AtomicInteger(map.size() > 0 ? map.size()-1 : 0); //put value in counter. For first -1
    }

    @Override
    public ConcurrentMap<Integer, Meal> getAllrecords() {
        return map;
    }
}
