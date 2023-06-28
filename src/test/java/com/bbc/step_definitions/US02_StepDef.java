package com.bbc.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class US02_StepDef {
    Response response;

    @Then("user verifies the id field is never null or empty for all fourteen items present in the data array")
    public void userVerifiesTheIdFieldIsNeverNullOrEmptyForAllFourteenItemsPresentInTheDataArray() {

       // String[] id = response.jsonPath().getString("id");
    }
    @And("user verifies the segment_type field for every track is always “music”")
    public void userVerifiesTheSegment_typeFieldForEveryTrackIsAlwaysMusic() {

    }

}
