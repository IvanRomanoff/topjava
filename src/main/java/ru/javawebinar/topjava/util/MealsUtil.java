package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class MealsUtil {

    public static ConcurrentMap<Integer, Meal> mealFactory() {
        List<Meal> list = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        ConcurrentMap<Integer, Meal> result = new ConcurrentHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Meal curr = list.get(i);
            curr.setId(i);
            result.put(i, curr);
        }
        return result;
    }

//    public static Map<Integer,MealWithExceed> getFilteredWithExceeded(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
//        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
//                .collect(
//                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
////                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
//                );
//
//        return meals.stream()
//                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
//                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
//                .collect(Collectors.toMap( ));
//    }

    public static ConcurrentMap<Integer ,MealWithExceed> getFilteredWithExceededByCycle(ConcurrentMap<Integer ,Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        final ConcurrentMap<LocalDate, Integer> caloriesSumByDate = new ConcurrentHashMap<>();
        meals.forEach( (key, val) -> caloriesSumByDate.merge(val.getDate(), val.getCalories(), Integer::sum));

        final ConcurrentMap<Integer ,MealWithExceed> mealsWithExceeded = new ConcurrentHashMap<>();
        meals.forEach((key, val) -> {
            if (TimeUtil.isBetween(val.getTime(), startTime, endTime)) {
                mealsWithExceeded.put(key, createWithExceed(val, caloriesSumByDate.get(val.getDate()) > caloriesPerDay));
            }
        });
        return mealsWithExceeded;
    }


    public static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), meal.getId(), exceeded);
    }
}