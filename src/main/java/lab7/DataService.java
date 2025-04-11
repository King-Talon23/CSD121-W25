package lab7;

import java.util.List;

public interface DataService {

    /**
     * Get all recipes from data storage
     * @return a list of recipes
     */
    List<Recipe> getRecipes() throws DataException;

    /**
     * An exception that is thrown when there is a problem with the underlying data storage
     */
    class DataException extends Exception {
        public DataException(Exception e) {
            super(e);
        }
    }
}
