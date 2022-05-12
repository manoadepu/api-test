package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class contains all the global utility methods that can be used accross all apis/tests
 */
public class GlobalUtility {
    /**
     *
     * @param coordinates
     * @return List<Integer>
     *
     * A method that takes comma seperated coordinates as a String and converts it to List<Integer> and returns
     */
    public static List<Integer> getCoordinatesFromCommaSeperatedString(String coordinates) {
        Integer[] transformedCordinates = {};
        if (coordinates.isEmpty()) {
            return Arrays.asList(transformedCordinates);
        } else {
            transformedCordinates = Stream.of(coordinates.trim().split(",")).map(Integer::valueOf).toArray(Integer[]::new);
        }
        return Arrays.asList(transformedCordinates);
    }
}
