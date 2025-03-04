import lab4.game.Col;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColFromTest {


    @Test
    void testValidInputs() {

        // testing what should be valid inputs for 1st column
        assertEquals(Col.Left, Col.from("1"));
        assertEquals(Col.Left, Col.from("l"));
        assertEquals(Col.Left, Col.from("L"));

        // 2nd column
        assertEquals(Col.Middle, Col.from("2"));
        assertEquals(Col.Middle, Col.from("m"));
        assertEquals(Col.Middle, Col.from("M"));
        assertEquals(Col.Middle, Col.from("c"));
        assertEquals(Col.Middle, Col.from("C"));

        // 3rd column
        assertEquals(Col.Right, Col.from("3"));
        assertEquals(Col.Right, Col.from("r"));
        assertEquals(Col.Right, Col.from("R"));
    }

    @Test
    void testInvalidInputs() {
        // testing what should be invalid
        assertThrows(IllegalArgumentException.class, () -> Col.from("x"));
        assertThrows(IllegalArgumentException.class, () -> Col.from(""));
        assertThrows(NullPointerException.class, () -> Col.from(null));
        assertThrows(IllegalArgumentException.class, () -> Col.from(" "));
        assertThrows(IllegalArgumentException.class, () -> Col.from("4"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("MLCR123"));
        // I think a regex added in somewhere could be make these valid
        assertThrows(IllegalArgumentException.class, () -> Col.from("12"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("1M"));

    }
}



