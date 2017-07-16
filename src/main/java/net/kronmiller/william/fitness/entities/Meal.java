package net.kronmiller.william.fitness.entities;

import java.sql.Date;
import java.util.List;

/**
 * Created by wkronmiller on 7/16/17.
 */
public class Meal {
    private Date creationDate;
    private String name;
    private List<FoodItem> entries;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodItem> getEntries() {
        return entries;
    }

    public void setEntries(List<FoodItem> entries) {
        this.entries = entries;
    }
    public int getTotalCalories() {
        return entries.stream()
                .map(item -> item.getKcals())
                .reduce(0, (a,b) -> a + b);
    }
}
