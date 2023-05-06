package Practice;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class FakeRestApiTest {

    @Test
    public void GetMethod() {
        baseURI = "https://fakerestapi.azurewebsites.net/api/v1";

        Response getResponse = get("/Activities");

        int statusCode = getResponse.getStatusCode();
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println(statusCode);
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println(getResponse.getTimeIn(TimeUnit.MICROSECONDS));
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println(getResponse.getBody().asString());
        System.out.println("------------------------------------------------------------------------------------------");


        /////////////////////////////////////statusCode validation///////////////////////////////////
        Assert.assertEquals(statusCode,200);
        get("/Activities").then().assertThat().statusCode(200);

        /////////////////////////////////////JSON Path validation///////////////////////////////////
        get("/Activities/1").then().body("id",equalTo(1));
        get("/Activities").then().body("[12].title",equalTo("Activity 13")).log().all();

        /////////////////////////////////////JSON Schema validation///////////////////////////////////
        ///// To Convert JSON to Json schema Use This Link ---->>  https://jsonformatter.org/json-to-jsonschema /////
        get("/Activities").then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/TestData/getActivitiesSchema.json")));

    }
}
