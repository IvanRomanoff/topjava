package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DaoMeal;
import ru.javawebinar.topjava.dao.DaoMealMemoryImplementation;
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
    DaoMeal myDao;

    @Override
    public void init() throws ServletException {
        myDao = new DaoMealMemoryImplementation();
        myDao.factoryMethod();

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("MealServlet doGet");
        req.setAttribute("mealMap", MealsUtil.getFilteredWithExceededByCycle(myDao.getAllrecords(), LocalTime.MIN, LocalTime.MAX, 2000));
        req.setAttribute("myDao", myDao);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("doPost");

        int calories = Integer.valueOf(req.getParameter("Calories"));
        String description = String.valueOf(req.getParameter("Description"));
        if (calories > 0 && !description.isEmpty()){
            myDao.add(new Meal(LocalDateTime.now()/*спешил*/, description, calories));
        }
        req.setAttribute("myDao", myDao);
        req.setAttribute("mealMap", MealsUtil.getFilteredWithExceededByCycle(myDao.getAllrecords(), LocalTime.MIN, LocalTime.MAX, 2000));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
