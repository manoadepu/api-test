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

    //cleaningSessions is used in given step definitions and the values are set according to the variables sent from feature file
    static CleaningSessions cleaningSessions = new CleaningSessions();
    static Response response = null;

    @Given("RoomSize is {string}")
    public void roomsize_is(String roomSize) {
        cleaningSessions.setRoomSize(GlobalUtility.getCoordinatesFromCommaSeperatedString(roomSize));
    }

    @Given("Coords are {string}")
    public void coords_are(String coords) {
        cleaningSessions.setCoords(GlobalUtility.getCoordinatesFromCommaSeperatedString(coords));

    }

    @Given("Patches are {string}")
    public void patches_to_be_cleaned_are(String patches) {

        /*
            This method takes patches from feature file
            format: "x-cord,y-cord&x-cord,y-cord&..x-cord,y-cord"
            converted to: [[x-cord,y-cord],[x-cord,y-cord]..[x-cord,y-cord]] that is set in the request body
         */

        String[] allPatches = patches.split("&");
        List<List<Integer>> patchesAfterTransformation = new ArrayList<>();

        for (String patch : allPatches) {
            patchesAfterTransformation.add(GlobalUtility.getCoordinatesFromCommaSeperatedString(patch));
        }

        cleaningSessions.setPatches(patchesAfterTransformation);

    }

    @Given("Moving instructions are {string}")
    public void moving_instruction_are(String instructions) {
        cleaningSessions.setInstructions(instructions);
    }

    @When("Make a cleaning-sessions POST call")
    public void make_cleaning_sessions_post_call() throws Exception {
        response = RestCallUtil.restAssuredPostCall(cleaningSessions,
                Endpoint.CLEANING_SESSIONS.getEndpoint());
    }

    @Then("validate that the reponse body has coords {string} and patches cleaned are {string}")
    public void validate_reponse_body(String coords, String expectedPatches) throws Exception {

        CleaningSessionResponseAssertionUtil.assertCleaningSessionResponseBody(response,
                GlobalUtility.getCoordinatesFromCommaSeperatedString(coords),
                Integer.parseInt(expectedPatches));

    }

    @Then("Validate that the reponse body returns status {string} and message {string}")
    public void validate_the_error_response(String status, String message) throws Exception {
        GlobalAssertionsUtil.validateErrorResponse(response, status, message);
    }
}