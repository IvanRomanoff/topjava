package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DaoMeal;
import ru.javawebinar.topjava.dao.DaoMealMemoryImplementation;
import ru.javawebinar.topjava.dao.MealService;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Администратор on 26.03.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    MealService mealService;

    @Override
    public void init() throws ServletException {
        DaoMeal myDao = new DaoMealMemoryImplementation();
        myDao.createNewMeals(MealsUtil.mealFactory());
        this.mealService = new MealService(myDao);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("MealServlet doGet");
        req.setAttribute("mealList", MealsUtil.getFilteredWithExceeded(mealService.getMeallist(), LocalTime.MIN, LocalTime.MAX, 2000));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("doPost");
        req.setAttribute("mealList", MealsUtil.getFilteredWithExceeded(mealService.getMeallist(), LocalTime.MIN, LocalTime.MAX, 2000));
//        LocalDateTime localDateTime = new LocalDateTime(); //
        int calories = Integer.valueOf(req.getParameter("Calories"));
        String description = String.valueOf(req.getParameter("Description"));
        mealService.addMeal(new Meal(LocalDateTime.now()/*спешил*/, description, calories));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
