package ru.javawebinar.topjava.util;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        ArrayList<UserMealWithExceed> a = (ArrayList<UserMealWithExceed>) getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        for (UserMealWithExceed list : a) {
            System.out.println(list.toString());
        }

    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        HashMap<LocalDate, Integer> meargetMap = new HashMap<LocalDate, Integer>();



        for (UserMeal list : mealList) {
            meargetMap.merge(list.getDateTime().toLocalDate(), Integer.valueOf(list.getCalories()), Integer::sum);
        }

        ArrayList<UserMealWithExceed> result = mealList.stream()
                .filter(val -> TimeUtil.isBetween(val.getDateTime().toLocalTime(), startTime, endTime))
                .map(list -> new UserMealWithExceed(list.getDateTime(),
                                                        list.getDescription(),
                                                        list.getCalories(),
                                                        meargetMap.get(list.getDateTime().toLocalDate()) > caloriesPerDay)).collect(Collectors.toCollection(ArrayList::new));
        return result;
    }
}