package FakeRestApiTest;

import FakeRestAPI.Activities;
import Utils.JsonFileManager;
import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;


@Epic("Fake REST API Test")
@Feature("Verify CRUD Operations on Activities module")
public class ActivitiesApiTest {
    Activities activities;
    JsonFileManager createActivityJson;
    JsonFileManager getActivityJson;
    JsonFileManager updateActivityJson;
     @BeforeMethod
    public void beforeMethod(){
         activities = new Activities();
         createActivityJson = new JsonFileManager("src/test/resources/TestData/ActivitiesTestData/createActivityTestData.json");
         getActivityJson = new JsonFileManager("src/test/resources/TestData/ActivitiesTestData/getActivityTestData.json");
         updateActivityJson = new JsonFileManager("src/test/resources/TestData/ActivitiesTestData/updateActivityTestData.json");
   }

    @Test(description = "get all Activities")
    @Story("GET Request")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description : Verify status Code equal:200 & Validate Json Schema ")
    public void getActivitiesTest() {
        activities.getActivities()
                .then()
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/TestData/ActivitiesTestData/getActivitiesSchema.json")))
                .log()
                .all();

    }

    @Test(description = "get Activity By ID")
    @Story("GET Request")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description : Verify title & ID & Validate Json Schema & Verify status Code equal:200 ")
    public void getActivityByIdTest() {
        activities.getActivitiesByID(1)
                .then()
                .body("id", equalTo(1))
                .and()
                .body("title", equalTo(getActivityJson.getTestData("title")))
                .and()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/TestData/ActivitiesTestData/getActivitySchema.json")))
                .statusCode(200)
                .log()
                .all();
    }

    @Test(description = "create New Activity")
    @Story("post Request")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description : Verify title & completed of new Activity")
    public void createActivity() {

        activities.postActivities(createActivityJson.getTestData("title"), Boolean.valueOf(createActivityJson.getTestData("completed")))
                .then()
                .and()
                .assertThat()
                .body("title", equalTo(createActivityJson.getTestData("title")))
                .body("completed", equalTo(Boolean.valueOf(createActivityJson.getTestData("completed"))))
                .log()
                .all();
    }

    @Test(description = "update Activity")
    @Story("put Request")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description : Verify title & completed of updated Activity")
    public void updateActivity() {
        Response Activities = activities.getActivities();
        int id = Activities.jsonPath().get("[0].id");

        activities.putActivities(id, updateActivityJson.getTestData("title"), Boolean.valueOf(updateActivityJson.getTestData("completed")))
                .then()
                .and()
                .assertThat()
                .body("title", equalTo(updateActivityJson.getTestData("title")))
                .body("completed", equalTo(Boolean.valueOf(updateActivityJson.getTestData("completed"))))
                .log()
                .all();
    }

    @Test(description = "delete Activity")
    @Story("delete Request")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description : Verify Verify status Code equal:200 when Delete Activity")
    public void deleteActivity() {
        Response Activities = activities.getActivities();
        int id = Activities.jsonPath().get("[0].id");

        activities.deleteActivities(id)
                .then()
                .and()
                .assertThat()
                .statusCode(200)
                .log()
                .all();
    }


}
