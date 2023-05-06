package FakeRestAPI;

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
    public Response getActivities() {
        return RestAssured
                .get("/Activities");
    }

    /**
     * get Activities By ID
     *
     * @param id Required ID
     * @return Activities By ID
     */
    public Response getActivitiesByID(int id) {
        return RestAssured
                .get("/Activities/" + id);
    }

    /**
     * create Activity Body
     *
     * @param title     String Value
     * @param completed Boolean Value (true || false)
     * @return Activity Body
     */
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
    public Response postActivities(String title, Boolean completed) {

        return RestAssured
                .given()
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

    public Response putActivities(int id, String title, Boolean completed) {
        return
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(activityBody(title, completed).toJSONString())
                        .when()
                        .put("/Activities/" + id);
    }

    /**
     * delete Activities
     *
     * @param id int value to choose which activity you want to delete
     * @return  delete Activity with status Code = 200 as the API documentation refer to
     */
    public Response deleteActivities(int id) {
        return
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .when()
                        .delete("/Activities/" + id);
    }


}
