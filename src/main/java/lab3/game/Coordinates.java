package lab3.game;

/**
 * Enum representing all possible coordinates on the board
 */
public enum Coordinates {
    A1("a1"), A2("a2"), A3("a3"),
    B1("b1"), B2("b2"), B3("b3"),
    C1("c1"), C2("c2"), C3("c3");

    private final String label;

        Coordinates(String label) {
        this.label = label;
                    }

    public String getLabel()
    {
        return label;
    }
}


