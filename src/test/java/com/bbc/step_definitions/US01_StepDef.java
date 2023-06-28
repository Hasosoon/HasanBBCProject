package com.bbc.step_definitions;

import com.bbc.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import static io.restassured.RestAssured.*;

public class US01_StepDef {
    Response response;
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


}
