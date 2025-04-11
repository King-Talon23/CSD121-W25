package lab7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Get all recipes that can be made in 15 minutes or less
     * @param dataService a service that provides access to recipe data
     * @return a list of quick recipes
     */
    public static List<Recipe> getQuickRecipes(DataService dataService) {
        try {
            var recipes = dataService.getRecipes();
            return recipes.stream().filter( r -> r.totalTime() <= 15 ).toList();
        } catch(Exception e ) {
            logger.error("Error while getting quick recipes: " + e.getMessage());
            logger.debug("Stack trace: " + Arrays.toString(e.getStackTrace()));
            return List.of();
        }
    }

    public static List<Recipe> searchRecipes(String searchTerm, DataService dataService) {
        List<Recipe> matchedRecipes = new ArrayList<>();

        try {
            List<Recipe> allRecipes = dataService.getRecipes();
            String lowerTerm = searchTerm.toLowerCase();

            for (Recipe recipe : allRecipes) {
                if (recipe.name().toLowerCase().contains(lowerTerm) ||
                        recipe.description().toLowerCase().contains(lowerTerm)) {
                    matchedRecipes.add(recipe);
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return matchedRecipes;
    }

    public static void main(String[] args) {
        var quickRecipes = getQuickRecipes(new SqliteDataService());
        System.out.println("Quick Recipes:");
        quickRecipes.forEach(System.out::println);
        var searchResults = searchRecipes("chicken", new SqliteDataService());
        System.out.println("\nSearch Results for 'chicken':");
        searchResults.forEach(System.out::println);
    }

}
