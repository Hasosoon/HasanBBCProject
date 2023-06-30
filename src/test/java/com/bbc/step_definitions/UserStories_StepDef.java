package com.bbc.step_definitions;

import com.bbc.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class UserStories_StepDef {
   public static Response response;
   RequestSpecification spec;


    @Given("user connects to the baseUrl")
    public void userConnectsToTheBaseUrl() {
        spec = new RequestSpecBuilder().setBaseUri(ConfigurationReader.get("apiUrl")).build();

    }

    @When("user sends GET request to the end point {string}")
    public void userSendsGETRequestToTheEndPoint(String endPoint) {
      response = given().spec(spec).when().get(endPoint);

    }

    @Then("user verifies status code is {int}")
    public void userVerifiesStatusCodeIs(int statusCode) {
        Assert.assertEquals(statusCode,response.statusCode());
    }

    @And("user verifies response time of the request is below {int} milliseconds")
    public void userVerifiesResponseTimeOfTheRequestIsBelowMilliseconds(long milliSecond) {
        long expectedResponseTime = response.getTime();
        System.out.println("expectedResponseTime = " + expectedResponseTime);
        Assert.assertFalse(response.getTime()<milliSecond);
    }

    @Then("user verifies the id field is never null or empty for all fourteen items present in the data array")
    public void userVerifiesTheIdFieldIsNeverNullOrEmptyForAllFourteenItemsPresentInTheDataArray() {

        JsonPath jsonPath = response.jsonPath();
        List<Map<String,Object>> dataArray = jsonPath.getList("data");

       for (int i = 0; i < dataArray.size(); i++) {
            Map<String,Object> item =dataArray.get(i);
            String id = (String) item.get("id");
           // System.out.println("id = " + id);
            Assert.assertTrue(id!=null);
            Assert.assertFalse(id.isEmpty());
       }
    }
    @And("user verifies the segment_type field for every track is always music")
    public void userVerifiesTheSegment_typeFieldForEveryTrackIsAlwaysMusic() {

        JsonPath jsonPath = response.jsonPath();
        List<Map<String,Object>> dataArray = jsonPath.getList("data");

        for (int i = 0; i < dataArray.size(); i++) {
            Map<String,Object> segment =dataArray.get(i);
            String segment_type = (String) segment.get("segment_type");
            //System.out.println("segment_type = " + segment_type);
            Assert.assertTrue(segment_type.equals("music"));
        }
    }


    @Then("user verifies the primary field in title_list is never null or empty for any track")
    public void userVerifiesThePrimaryFieldInTitle_listIsNeverNullOrEmptyForAnyTrack() {

        JsonPath jsonPath = response.jsonPath();
        List<Map<String,Object>> dataArray = jsonPath.getList("data");

        for (int i = 0; i < dataArray.size(); i++) {
            String primaryValues = jsonPath.getString("data[" + i + "].title_list.primary");
            System.out.println(primaryValues);
            Assert.assertTrue(primaryValues!=(null));
            Assert.assertFalse(primaryValues.isEmpty());
        }

    }

    @Then("user verifies that only one track in the list has now_playing field in offset as true")
    public void userVerifiesThatOnlyOneTrackInTheListHasNow_playingFieldInOffsetAsTrue() {


        JsonPath jsonPath = response.jsonPath();
        List<Map<String,Object>> dataArray = jsonPath.getList("data");
         int count=0;
        for (int i = 0; i < dataArray.size(); i++) {
            Boolean offsetValues = jsonPath.getBoolean("data["+i+"].offset.now_playing");
            System.out.println(offsetValues);
            if (offsetValues==true){
                count++;
            }
        }
           Assert.assertEquals(count,1);
    }

    @Then("user verifies in the response headers contain Date value")
    public void userVerifiesInTheResponseHeadersContainDateValue() {
        String date = String.valueOf(response.headers().get("Date"));
        System.out.println("date = " + date);
        Assert.assertEquals("Date=Fri, 21 May",date);
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
    }
}
