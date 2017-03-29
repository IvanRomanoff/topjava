package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by Администратор on 28.03.2017.
 */
public interface DaoMeal {

    public void add(Meal meal);

    public Meal get(int id);

    public void update(Meal meal);

    public void remove(int id);

    public void factoryMethod();

    public ConcurrentMap<Integer, Meal> getAllrecords();
}