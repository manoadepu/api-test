package util.assertion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import io.restassured.response.Response;
import model.error.ErrorResponse;
import org.testng.Assert;

/**
 * This class contains globas assertion utility methods that can be used accross all the tests
 */
public class GlobalAssertionsUtil {
    public static void validateErrorResponse(Response response, String expectedStatus, String expectedMessage) throws Exception {


        if (response.getStatusCode() != 200) {

            try {
                ObjectMapper mapper = new ObjectMapper();
                ErrorResponse errorResponse = mapper.readValue(response.getBody().asString(), ErrorResponse.class);

                Assert.assertTrue(Integer.parseInt(expectedStatus) == errorResponse.getStatus(), "Status is incorrect in error response.");
                Assert.assertTrue(expectedMessage.equals(errorResponse.getMessage()), "Expected message is incorrect");
            } catch (MismatchedInputException exception) {
                Assert.fail("Error Response body not received");
            }

        } else {
            Assert.fail("Expected error response but received status code: " + response.getStatusCode());
        }

    }

}