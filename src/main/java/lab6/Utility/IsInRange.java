package lab6.Utility;

public class IsInRange {

    public static Boolean Int(Integer intToCheck, Integer min, Integer max){
        for (int i = min; i<max; i++){
            if (i == intToCheck) {
                return true;
            }
        }
        return false;
    }
}
