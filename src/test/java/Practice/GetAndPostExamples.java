package Practice;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class GetAndPostExamples {

    @BeforeMethod
    public void beforeMethod(){
        baseURI= "https://fakerestapi.azurewebsites.net/api/v1";
    }

    @Test
    public void GetTest(){
         get("/Activities/1")
                 .then()
                 .statusCode(200)
                 .and()
                 .body("id",equalTo(1))
                 .body("title",equalTo("Activity 1"))
                 .log()
                 .all();
    }


    @Test
    public void PostTest(){

        JSONObject Body = new JSONObject();
        Body.put("id","1");
        Body.put("title","Ahmed");
        Body.put("dueDate","2023-03-06T14:48:56.672Z");
        Body.put("completed",true);

        given()
                .contentType(ContentType.JSON)
                .body(Body.toJSONString())
                .when()
                .post("/Activities")
                .then()
                .and()
                .assertThat()
                .body("title",equalTo("Ahmed"))
                .log()
                .all();
    }


}
