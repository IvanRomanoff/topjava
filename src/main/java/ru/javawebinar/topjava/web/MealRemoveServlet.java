package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.DaoMeal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

/**
 * Created by Администратор on 30.03.2017.
 */
public class MealRemoveServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealRemoveServlet.class);
    DaoMeal myDao;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("start removing Meal from Map");
        myDao = (DaoMeal) req.getAttribute("myDao");
        myDao.remove(Integer.valueOf(req.getParameter("ID")));

        req.setAttribute("myDao",myDao);
        req.setAttribute("mealMap", MealsUtil.getFilteredWithExceededByCycle(myDao.getAllrecords(), LocalTime.MIN, LocalTime.MAX, 2000));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
        LOG.debug("end removing Meal from Map");
    }
}
