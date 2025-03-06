package lab4.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColTest {

    @Test
    void fromValidStringHandling() {
        assertEquals(Col.Left, Col.from( "1"));
        assertEquals(Col.Middle, Col.from( "2"));
        assertEquals(Col.Right, Col.from( "3"));
        assertEquals(Col.Left, Col.from( "l"));
        assertEquals(Col.Middle, Col.from( "m"));
        assertEquals(Col.Middle, Col.from( "c"));
        assertEquals(Col.Right, Col.from( "r"));
    }

    @Test
    void fromIsCaseInsensitive() {
        assertEquals(Col.Left, Col.from("L"));
        assertEquals(Col.Left, Col.from("l"));
        assertEquals(Col.Middle, Col.from( "M"));
        assertEquals(Col.Middle, Col.from( "m"));
        assertEquals(Col.Middle, Col.from( "C"));
        assertEquals(Col.Middle, Col.from( "c"));
        assertEquals(Col.Right, Col.from( "R"));
        assertEquals(Col.Right, Col.from( "r"));

    }

    @Test
    void fromInvalidStringException() {
        assertThrows(IllegalArgumentException.class, () -> Col.from("4"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("-1"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("A"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("hello"));
        assertThrows(IllegalArgumentException.class, () -> Col.from(""));
        assertThrows(IllegalArgumentException.class, () -> Col.from("123324535436"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("LM"));
        assertThrows(IllegalArgumentException.class, () -> Col.from(" "));
        assertThrows(IllegalArgumentException.class, () -> Col.from("L "));
    }
}