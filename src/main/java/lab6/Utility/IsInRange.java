package lab6.Utility;

public class IsInRange {

    public static Boolean isIntInRange(Integer intToCheck, Integer min, Integer max){
        return (min <= intToCheck && intToCheck <= max);
    }
}
