package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.request.CleaningSessions;

import java.util.List;

/**
 * This is a Rest call utility that can be used by all the tests to perform GET/POST etc calls by adding a generic method as reqired.
 */
public class RestCallUtil {
    /**
     * @param roomSize
     * @param coords
     * @param patches
     * @param instructions
     * @return
     * @throws JsonProcessingException This method makes a POST call using rest-assured
     */
    public static String buildCleaningSessionsRequest(List<Integer> roomSize,
                                                      List<Integer> coords,
                                                      List<List<Integer>> patches,
                                                      String instructions) throws JsonProcessingException {
        return new ObjectMapper().writer().writeValueAsString(new CleaningSessions(roomSize, coords, patches, instructions));
    }


    public static Response restAssuredPostCall(CleaningSessions cleaningSessions, String endpoint) throws Exception {
        RestAssured.baseURI = System.getenv("BASE_URI");
        Response response = null;

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestAsJsonString = ow.writeValueAsString(cleaningSessions);

        try {
            System.out.println("Request: " + requestAsJsonString);
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestAsJsonString)
                    .post(endpoint);
            System.out.println("Response: " + response.getBody().asString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


}
