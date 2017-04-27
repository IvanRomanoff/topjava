package ru.javawebinar.topjava.repository.datajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {
private static final Logger LOG = LoggerFactory.getLogger(DataJpaMealRepositoryImpl.class);
    {
        LOG.info("DataJpaMealRepositoryImpl has started");
    }


    @Autowired
    private CrudMealRepository crudMealRepository;

    @Autowired
    private DataJpaUserRepositoryImpl crudUserRepository;

    @Override
    public Meal save(Meal meal, int userId) {
        if (!meal.isNew() && (get(meal.getId(), userId) == null)) {
            return null;
        }

        User user = crudUserRepository.get(userId);
        meal.setUser(user);

        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudMealRepository.delete(id) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = crudMealRepository.findOne(id);
        return meal != null && meal.getUser().getId() == userId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudMealRepository.findAllByUser(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudMealRepository.findByUserIdBetweenDates(startDate, endDate, userId);
    }
}
