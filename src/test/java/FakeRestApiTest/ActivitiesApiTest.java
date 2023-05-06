package FakeRestApiTest;

import FakeRestAPI.Activities;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class ActivitiesApiTest {

    Activities activities = new Activities();

    @Test(description = "get all Activities")
    public void getActivitiesTest() {
        Response getActivities = activities.getActivities();
        Assert.assertEquals(getActivities.statusCode(), 200);
        activities.getActivities()
                .then()
                .log()
                .all();

    }

    @Test(description = "get Activity By ID")
    public void getActivitiesByIdTest() {
        Response getActivitiesById = activities.getActivitiesByID(1);
        getActivitiesById.then()
                .body("id", equalTo(1))
                .and()
                .body("title", equalTo("Activity 1"))
                .log()
                .all();
    }

    @Test(description = "create New Activity")
    public void createActivity() {

        activities.postActivities("Ahmed", true)
                .then()
                .and()
                .assertThat()
                .body("title", equalTo("Ahmed"))
                .body("completed", equalTo(true))
                .log()
                .all();
    }

    @Test(description = "update Activity")
    public void updateActivity() {
        Response Activities = activities.getActivities();
        int id = Activities.jsonPath().get("[0].id");

        activities.putActivities(id, "ali", false)
                .then()
                .and()
                .assertThat()
                .body("title", equalTo("ali"))
                .body("completed", equalTo(false))
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
