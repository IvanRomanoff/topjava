package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> findAllByUser(@Param("userId") int userId);

    @Transactional
    @Override
    Meal save(Meal entity);

    @Override
    Meal findOne(Integer integer);

    @Transactional
    @Modifying
    @Query("DELETE from Meal m WHERE m.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId and m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<Meal> findByUserIdBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate , @Param("userId") int userId);
}
