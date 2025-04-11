package lab7;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteDataService implements DataService {

    @Override
    public List<Recipe> getRecipes() throws DataException {

        try {
            List<Recipe> recipes = new ArrayList<>();
            var connection = DriverManager.getConnection("jdbc:sqlite:recipes.sqlite");
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery("SELECT * FROM recipes");
            while (resultSet.next()) {
                recipes.add(new Recipe(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("instructions"),
                        resultSet.getInt("servings"),
                        resultSet.getInt("prep_time"),
                        resultSet.getInt("cook_time"),
                        resultSet.getInt("total_time")
                ));
            }
            return recipes;
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }
}
