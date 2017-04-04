package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;
    
    public Meal save(Meal meal, int userID) {
        return service.save(meal,userID);
    }

    public void delete(int id, int userID) {
        service.delete(id,userID);
    }

    public Meal get(int id, int userID) {
        return service.get(id,userID);
    }

    public Collection<Meal> getAll(int userID) {
        return service.getAll(userID);
    }
}