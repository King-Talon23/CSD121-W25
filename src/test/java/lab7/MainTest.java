package lab7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoData() {
        var recipes = Main.getQuickRecipes(List::of);
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoQuickRecipes() {
        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 16),
                new Recipe(1, "", "", "", 4, 10, 10, 20),
                new Recipe(2, "", "", "", 4, 10, 10, 200)
        ));
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsAllRecipesIfAllQuick() {

        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 15),
                new Recipe(1, "", "", "", 4, 10, 10, 1),
                new Recipe(2, "", "", "", 4, 10, 10, 10)
        ));

        assertEquals(3, recipes.size());
    }

    @Test
    void testGetQuickRecipesWorksOnTypicalData() {

        var recipes = Main.getQuickRecipes(() -> List.of(
                        new Recipe(0, "", "", "", 4, 10, 10, 10),
                        new Recipe(1, "", "", "", 4, 10, 10, 15),
                        new Recipe(2, "", "", "", 4, 10, 10, 16),
                        new Recipe(3, "", "", "", 4, 10, 10, 20),
                        new Recipe(4, "", "", "", 4, 10, 10, 2343)
        ));

        assertEquals(2, recipes.size());

        // Verify that the two recipes we expected are in fact in the list
        assertEquals(0, recipes.get(0).id());
        assertEquals(1, recipes.get(1).id());
    }

    @Test
    void testSearchRecipesFindsMatchInName() {
        var recipes = Main.searchRecipes("chicken", () -> List.of(
                new Recipe(0, "Chicken Soup", "", "", 4, 10, 10, 15),
                new Recipe(1, "Beef Stew", "", "", 4, 10, 10, 25)
        ));
        assertEquals(1, recipes.size());
        assertEquals("Chicken Soup", recipes.get(0).name());
    }

    @Test
    void testSearchRecipesFindsMatchInDescription() {
        var recipes = Main.searchRecipes("chicken", () -> List.of(
                new Recipe(0, "Soup", "Chicken based broth", "", 4, 10, 10, 15),
                new Recipe(1, "Stew", "Rich beef flavor", "", 4, 10, 10, 25)
        ));
        assertEquals(1, recipes.size());
        assertEquals("Soup", recipes.get(0).name());
    }

    @Test
    void testSearchRecipesNoMatchFound() {
        var recipes = Main.searchRecipes("banana", () -> List.of(
                new Recipe(0, "Pasta", "Tomato sauce", "", 4, 10, 10, 15)
        ));
        assertTrue(recipes.isEmpty());
    }

    @Test
    void testSearchRecipesIsCaseInsensitive() {
        var recipes = Main.searchRecipes("CHICKEN", () -> List.of(
                new Recipe(0, "chicken sandwich", "", "", 4, 10, 10, 15)
        ));
        assertEquals(1, recipes.size());
        assertEquals("chicken sandwich", recipes.get(0).name());
    }

    @Test
    void testSearchRecipesHandlesExceptionsGracefully() {
        var recipes = Main.searchRecipes("anything", () -> {
            throw new DataService.DataException(new RuntimeException("Database failed"));
        });
        assertNotNull(recipes);
        assertTrue(recipes.isEmpty());
    }


}