package FakeRestAPI;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class Activities {

    public Activities() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
    }

    /**
     * get Activities
     *
     * @return all Activities
     */
    @Step("get Activities")
    public Response getActivities() {
        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .get("/Activities");
    }

    /**
     * get Activities By ID
     *
     * @param id Required ID
     * @return Activities By ID
     */
    @Step("get Activities By ID : [{id}]")

    public Response getActivitiesByID(int id) {
        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .get("/Activities/" + id);
    }

    /**
     * create Activity Body
     *
     * @param title     String Value
     * @param completed Boolean Value (true || false)
     * @return Activity Body
     */
    @Step("create Activity Body  title: [{title}] , completed [{completed}] ")
    private JSONObject activityBody(String title, Boolean completed) {
        JSONObject Activity = new JSONObject();
        Activity.put("title", title);
        Activity.put("completed", completed);
        return Activity;
    }

    /**
     * post Activities
     *
     * @param title     String Value
     * @param completed Boolean Value (true || false)
     * @return create new Activity
     */
    @Step("post Activities title: [{title}] , completed [{completed}]")
    public Response postActivities(String title, Boolean completed) {

        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(activityBody(title, completed).toJSONString())
                .when()
                .post("/Activities");
    }

    /**
     * put Activities
     *
     * @param id        int value to choose which activity you want to update
     * @param title     String value
     * @param completed Boolean Value (true || false)
     * @return updated activity
     */

    @Step("put Activities id:[{id}] , title: [{title}] , completed [{completed}]")
    public Response putActivities(int id, String title, Boolean completed) {
        return
                RestAssured
                        .given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .body(activityBody(title, completed).toJSONString())
                        .when()
                        .put("/Activities/" + id);
    }

    /**
     * delete Activities
     *
     * @param id int value to choose which activity you want to delete
     * @return delete Activity with status Code = 200 as the API documentation refer to
     */
    @Step("delete Activities id: [{id}]")
    public Response deleteActivities(int id) {
        return
                RestAssured
                        .given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .when()
                        .delete("/Activities/" + id);
    }


}
