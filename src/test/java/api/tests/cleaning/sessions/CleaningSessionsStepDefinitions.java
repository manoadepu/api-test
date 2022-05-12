package api.tests.cleaning.sessions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import model.request.CleaningSessions;
import util.GlobalUtility;
import util.RestCallUtil;
import util.assertion.CleaningSessionResponseAssertionUtil;
import util.assertion.GlobalAssertionsUtil;
import util.enums.Endpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains step definitions for v1 cleaning-sessions endpoint
 */
public class CleaningSessionsStepDefinitions {


    static CleaningSessions cleaningSessions = new CleaningSessions();
    static Response response = null;

    /**
     * @param roomSize
     * Step definition that takes roomSize coords as a string and sets it to request CleaningSession request body
     */
    @Given("RoomSize is {string}")
    public void roomsize_is(String roomSize) {
        cleaningSessions.setRoomSize(GlobalUtility.getCoordinatesFromCommaSeperatedString(roomSize));
    }

    /**
     * @param coords
     * Step definition that takes coords as String and sets it to request CleaningSession request body
     */
    @Given("Coords are {string}")
    public void coords_are(String coords) {

        cleaningSessions.setCoords(GlobalUtility.getCoordinatesFromCommaSeperatedString(coords));
    }

    /**
     * @param patches
     * Step definition that takes patches as String and sets it to request CleaningSession request body
     */
    @Given("Patches are {string}")
    public void patches_to_be_cleaned_are(String patches) {
        String[] allPatches = patches.split("&");
        List<List<Integer>> patchesAfterTransformation = new ArrayList<>();

        for (String patch : allPatches) {
            patchesAfterTransformation.add(GlobalUtility.getCoordinatesFromCommaSeperatedString(patch));
        }

        cleaningSessions.setPatches(patchesAfterTransformation);
    }

    /**
     * @param instructions
     * Step definition that takes instructions
     */
    @Given("Moving instructions are {string}")
    public void moving_instruction_are(String instructions) {
        cleaningSessions.setInstructions(instructions);
    }

    /**
     * @throws Exception
     * When Step definition that makes a post call
     */
    @When("Make a cleaning-sessions POST call")
    public void make_cleaning_sessions_post_call() throws Exception {
        response = RestCallUtil.restAssuredPostCall(cleaningSessions,
                Endpoint.CLEANING_SESSIONS.getEndpoint());
    }

    /**
     *
     * @param coords
     * @param expectedPatches
     * @throws Exception
     * Then Step definition that takes expected coords and expected patches and asserts using Assertion utilities
     */
    @Then("validate that the reponse body has coords {string} and patches cleaned are {string}")
    public void validate_that_the_reponse_body_has_coords_and_patches_cleaned_are(String coords, String expectedPatches) throws Exception {

        CleaningSessionResponseAssertionUtil.assertCleaningSessionResponseBody(response,
                GlobalUtility.getCoordinatesFromCommaSeperatedString(coords),
                Integer.parseInt(expectedPatches));

    }

    /**
     *
     * @param status
     * @param message
     * @throws Exception
     * Then step definition that validates Error Response
     */
    @Then("Validate that the reponse body returns status {string} and message {string}")
    public void validate_the_error_response(String status, String message) throws Exception {
        GlobalAssertionsUtil.validateErrorResponse(response, status, message);
    }

}
