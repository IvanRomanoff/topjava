package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;
import java.util.Collection;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal, int userID) {
        return repository.save(meal, userID);
    }

    @Override
    public void delete(int id, int userID) {
        repository.delete(id, userID);
    }

    @Override
    public Meal get(int id, int userID) {
       return ValidationUtil.checkNotFoundWithId(repository.get(id, userID), userID);

    }

    @Override
    public Collection<Meal> getAll(int userID) {
        return repository.getAll(userID);
    }
}