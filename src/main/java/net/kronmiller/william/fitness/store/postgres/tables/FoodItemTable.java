package net.kronmiller.william.fitness.store.postgres.tables;

import net.kronmiller.william.fitness.entities.FoodItem;
import net.kronmiller.william.fitness.entities.User;
import net.kronmiller.william.fitness.exceptions.AppException;
import net.kronmiller.william.fitness.store.postgres.DatabaseReference;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

interface ColumnNames {
    String UUID = "food_item_uuid",
            SHORT_NAME = "short_name",
            DESCRIPTION = "description",
            KCALS = "kcals",
            CARBS = "carbs",
            FAT = "fat",
            PROTEIN = "protein",
            CHOLESTEROL = "cholesterol",
            SUGAR = "sugar";
    String schema = String.format("(%s SERIAL PRIMARY KEY, " +
                    "%s TEXT NOT NULL UNIQUE, " +
                    "%s TEXT, " +
                    "%s INTEGER NOT NULL, " +
                    "%s REAL, " +
                    "%s REAL, " +
                    "%s REAL, " +
                    "%s REAL, " +
                    "%s REAL)",
            UUID,
            SHORT_NAME,
            DESCRIPTION,
            KCALS,
            CARBS,
            FAT,
            PROTEIN,
            CHOLESTEROL,
            SUGAR);
    String columns = String.format("(%s, %s, %s, %s, %s, %s, %s, %s, %s)",
            UUID,
            SHORT_NAME,
            DESCRIPTION,
            KCALS,
            CARBS,
            FAT,
            PROTEIN,
            CHOLESTEROL,
            SUGAR);
}
/**
 * Created by wkronmiller on 7/15/17.
 *
 * ___________________________________________________________________________________________________
 * | food_item_uuid | short_name | description | kcals | carbs | fat | protein | cholesterol | sugar |
 * |----------------|--------------------------------------------------------------------------------|
 * |                |                                                                                |
 * |                |                                                                                |
 * |________________|________________________________________________________________________________|
 *
 */
public class FoodItemTable extends Table implements ColumnNames {
    public FoodItemTable(String tableName, DatabaseReference database) throws SQLException {
       super(tableName, database);
    }

    /**
     * Insert item and return with UUID
     * @param foodItem
     * @return
     * @throws AppException
     * @throws SQLException
     */
    public FoodItem addItem(FoodItem foodItem) throws AppException, SQLException {
        String query = "INSERT INTO " + tableName + columns +
                " VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING " + UUID;
        PreparedStatement preparedStatement = database.prepareStatement(query);
        preparedStatement.setString(1, foodItem.getShortName());
        preparedStatement.setString(2, foodItem.getDescription());
        preparedStatement.setInt(3, foodItem.getKcals());
        preparedStatement.setDouble(4, foodItem.getCarbs());
        preparedStatement.setDouble(5, foodItem.getFat());
        preparedStatement.setDouble(6, foodItem.getProtein());
        preparedStatement.setDouble(7, foodItem.getCholesterol());
        preparedStatement.setDouble(8, foodItem.getSugar());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        foodItem.setUuid(resultSet.getInt(UUID));
        return foodItem;
    }

    public FoodItem getItem(FoodItem foodItem) throws AppException, SQLException {
        String query = "SELECT " + columns + " FROM " + tableName +
                " WHERE " + UUID + " = ?";
        PreparedStatement preparedStatement = database.prepareStatement(query);
        preparedStatement.setInt(1, foodItem.getUuid());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        foodItem.setShortName(resultSet.getString(SHORT_NAME));
        foodItem.setDescription(resultSet.getString(DESCRIPTION));
        foodItem.setKcals(resultSet.getInt(KCALS));
        foodItem.setCarbs(resultSet.getDouble(CARBS));
        foodItem.setFat(resultSet.getDouble(FAT));
        foodItem.setProtein(resultSet.getDouble(PROTEIN));
        foodItem.setCholesterol(resultSet.getDouble(CHOLESTEROL));
        foodItem.setSugar(resultSet.getDouble(SUGAR));
        return foodItem;
    }

    @Override
    protected String getSchema() {
        return schema;
    }
}
