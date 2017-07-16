package net.kronmiller.william.fitness.entities;

import java.sql.Date;
import java.util.List;

/**
 * Created by wkronmiller on 7/16/17.
 */
public class DiaryDay {
    private Date date;
    private List<Meal> meals;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}

