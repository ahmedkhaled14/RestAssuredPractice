package Practice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutAndDeleteExamples {

    @BeforeMethod
    public void beforeMethod() {
        baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
    }

    @Test
    public void PutTest() {
        JSONObject EditedBody = new JSONObject();
        EditedBody.put("title", "Ahmed");
        EditedBody.put("dueDate", "2023-03-06T14:48:56.672Z");
        EditedBody.put("completed", true);

        given()
                .contentType(ContentType.JSON)
                .body(EditedBody.toJSONString())
                .when()
                .put("/Activities/1")
                .then()
                .assertThat()
                .body("title", equalTo("Ahmed"))
                .statusCode(200)
                .log()
                .all();

    }


    @Test
    public void DeleteTest() {
        Response Delete = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .when()
                .delete("/Activities/1");

        int statusCode = Delete.getStatusCode();
        Assert.assertEquals(statusCode, 200);




    }


}
