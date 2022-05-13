package util.assertion;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import model.response.CleaningSessionsResponse;
import org.testng.Assert;

import java.util.List;

/**
 * This class contains assertions for cleaningSessions API
 */
public class CleaningSessionResponseAssertionUtil {

    /**
     * @param response
     * @param expectedCoords
     * @param expectedPatches
     * @throws Exception This method takes rest-assured response object and expected coords and patches and performs assertions
     */
    public static void assertCleaningSessionResponseBody(Response response, List<Integer> expectedCoords, int expectedPatches) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CleaningSessionsResponse cleaningSessionsResponse = mapper.readValue(response.getBody().asString(), CleaningSessionsResponse.class);

        Assert.assertTrue(cleaningSessionsResponse.getCoords().equals(expectedCoords), "Coords incorrect in response![Expected:" + expectedCoords.toString() + " Actual:" + cleaningSessionsResponse.getCoords().toString() + "]");

        Assert.assertTrue(cleaningSessionsResponse.getPatches() == expectedPatches, "Number of patches cleaned are incorrect![Expected:" + expectedPatches + " Actual:" + cleaningSessionsResponse.getPatches() + "]");

    }
}
