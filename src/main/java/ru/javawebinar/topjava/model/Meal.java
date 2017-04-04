package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class Meal extends NamedEntity{
    private final LocalDateTime dateTime;

    private final int calories;

    private volatile int userID;

    public Meal(LocalDateTime dateTime, String name, int calories/*, int userID*/) {
        this(null, dateTime, name, calories/*, userID*/);
    }

    public Meal(Integer id, LocalDateTime dateTime, String name, int calories/*, int userID*/) {
        this.id = id;
        this.dateTime = dateTime;
        this.name = name;
        this.calories = calories;
//        this.userID = userID;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
