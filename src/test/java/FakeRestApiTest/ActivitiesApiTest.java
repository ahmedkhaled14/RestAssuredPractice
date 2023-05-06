package FakeRestApiTest;

import FakeRestAPI.Activities;
import Utils.JsonFileManager;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class ActivitiesApiTest {
    Activities activities = new Activities();
    JsonFileManager createActivityJson = new JsonFileManager("src/test/resources/TestData/createActivityTestData.json");
    JsonFileManager getActivityJson = new JsonFileManager("src/test/resources/TestData/getActivityTestData.json");
    JsonFileManager updateActivityJson = new JsonFileManager("src/test/resources/TestData/updateActivityTestData.json");

    @Test(description = "get all Activities")
    public void getActivitiesTest() {
        Response getActivities = activities.getActivities();
        Assert.assertEquals(getActivities.statusCode(), 200);
        activities.getActivities()
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/TestData/getActivitiesSchema.json")))
                .log()
                .all();

    }

    @Test(description = "get Activity By ID")
    public void getActivitiesByIdTest() {
        Response getActivitiesById = activities.getActivitiesByID(1);
        getActivitiesById.then()
                .body("id", equalTo(1))
                .and()
                .body("title", equalTo(getActivityJson.getTestData("title")))
                .and()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/TestData/getActivitySchema.json")))
                .statusCode(200)
                .log()
                .all();
    }

    @Test(description = "create New Activity")
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
