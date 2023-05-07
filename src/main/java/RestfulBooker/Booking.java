package RestfulBooker;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class Booking {


    public Booking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
    }

    /**
     * login Body
     *
     * @param username String Value from src/test/resources/TestData/BookingsTestDate/loginBooking.json
     * @param password String Value from src/test/resources/TestData/BookingsTestDate/loginBooking.json
     * @return loginB ody
     */
    private JSONObject loginBody(String username , String password) {
        JSONObject login = new JSONObject();
        login.put("username", username);
        login.put("password", password);
        return login;
    }

    /**
     * authentication
     *
     * @param username String Value from src/test/resources/TestData/BookingsTestDate/loginBooking.json
     * @param password String Value from src/test/resources/TestData/BookingsTestDate/loginBooking.json
     * @return authentication
     */
    @Step("authentication")
    public Response authentication(String username , String password  ) {
        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(loginBody(username ,password))
                .post("/auth");
    }

    /**
     * get BookingI ds
     *
     * @return Booking Ids
     */
    @Step("get Booking Ids")
    public Response getBookingIds() {
        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .get("booking");
    }

    /**
     * get Specific Booking id
     *
     * @param id int value
     * @return Specific Booking id
     */
    @Step("get Specific Booking id:[{id}]")
    public Response getSpecificBooking(int id) {
        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .get("booking/" + id);
    }

    /**
     * createBookingBody
     *
     * @param firstname       String Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param lastname        String Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param totalprice      int Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param depositpaid     boolean Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param checkinDate     String Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param checkoutDate    String Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param additionalneeds String Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @return create Booking Body
     */
    @Step("create Booking Body firstname:[{firstname}] , lastname:[{lastname}] ,totalprice:[{totalprice}] ,depositpaid:[{depositpaid}] ,checkinDate:[{checkinDate}] ,checkoutDate:[{checkoutDate}] ,additionalneeds:[{additionalneeds}]  ")
    private JSONObject createBookingBody(String firstname, String lastname, int totalprice, boolean depositpaid,
                                         String checkinDate, String checkoutDate, String additionalneeds) {
        JSONObject createBookingBody = new JSONObject();
        createBookingBody.put("firstname", firstname);
        createBookingBody.put("lastname", lastname);
        createBookingBody.put("totalprice", totalprice);
        createBookingBody.put("depositpaid", depositpaid);
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", checkinDate);
        bookingdates.put("checkout", checkoutDate);
        createBookingBody.put("bookingdates", bookingdates);
        createBookingBody.put("additionalneeds", additionalneeds);
        return createBookingBody;
    }

    /**
     * create Booking
     *
     * @param firstname       String Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param lastname        String Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param totalprice      int Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param depositpaid     boolean Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param checkinDate     String Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param checkoutDate    String Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @param additionalneeds String Value from src/test/resources/TestData/BookingsTestDate/createBooking.json
     * @return create Booking
     */
    @Step("create Booking ==> firstname:[{firstname}] , lastname:[{lastname}] ,totalprice:[{totalprice}] ,depositpaid:[{depositpaid}] ,checkinDate:[{checkinDate}] ,checkoutDate:[{checkoutDate}] ,additionalneeds:[{additionalneeds}]  ")
    public Response createBooking(String firstname, String lastname, int totalprice, boolean depositpaid,
                                  String checkinDate, String checkoutDate, String additionalneeds) {
        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(createBookingBody(firstname, lastname, totalprice, depositpaid,
                        checkinDate, checkoutDate, additionalneeds))
                .post("booking");
    }

    /**
     * updated Booking Body
     *
     * @param updatedFirstname       String Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedLastname        String Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedTotalprice      int Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedDepositpaid     boolean Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedCheckinDate     String Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedCheckoutDate    String Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedAdditionalneeds String Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @return updated Booking Body
     */
    @Step("updated Booking Body updatedFirstname:[{updatedFirstname}] , updatedLastname:[{updatedLastname}] ,updatedTotalprice:[{updatedTotalprice}] ,updatedDepositpaid:[{updatedDepositpaid}] ,updatedCheckinDate:[{updatedCheckinDate}] ,updatedCheckoutDate:[{updatedCheckoutDate}] ,updatedAdditionalneeds:[{updatedAdditionalneeds}]  ")
    private JSONObject updatedBookingBody(String updatedFirstname, String updatedLastname, int updatedTotalprice, boolean updatedDepositpaid,
                                          String updatedCheckinDate, String updatedCheckoutDate, String updatedAdditionalneeds) {
        JSONObject updatedBookingBody = new JSONObject();
        updatedBookingBody.put("firstname", updatedFirstname);
        updatedBookingBody.put("lastname", updatedLastname);
        updatedBookingBody.put("totalprice", updatedTotalprice);
        updatedBookingBody.put("depositpaid", updatedDepositpaid);
        JSONObject Updatedbookingdates = new JSONObject();
        updatedBookingBody.put("checkin", updatedCheckinDate);
        updatedBookingBody.put("checkout", updatedCheckoutDate);
        updatedBookingBody.put("bookingdates", Updatedbookingdates);
        updatedBookingBody.put("additionalneeds", updatedAdditionalneeds);
        return updatedBookingBody;
    }

    /**
     * update Booking
     *
     * @param bookingId              int value to choose which booking you want to update
     * @param updatedFirstname       String Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedLastname        String Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedTotalprice      int Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedDepositpaid     boolean Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedCheckinDate     String Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedCheckoutDate    String Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @param updatedAdditionalneeds String Value from src/test/resources/TestData/BookingsTestDate/updateBooking.json
     * @return updated Booking
     */
    @Step("updated Booking ==> updatedFirstname:[{updatedFirstname}] , updatedLastname:[{updatedLastname}] ,updatedTotalprice:[{updatedTotalprice}] ,updatedDepositpaid:[{updatedDepositpaid}] ,updatedCheckinDate:[{updatedCheckinDate}] ,updatedCheckoutDate:[{updatedCheckoutDate}] ,updatedAdditionalneeds:[{updatedAdditionalneeds}]  ")
    public Response updateBooking(String token,int bookingId, String updatedFirstname, String updatedLastname, int updatedTotalprice
            , boolean updatedDepositpaid, String updatedCheckinDate, String updatedCheckoutDate, String updatedAdditionalneeds) {

        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie(token)
                .body(updatedBookingBody(updatedFirstname, updatedLastname, updatedTotalprice, updatedDepositpaid,
                        updatedCheckinDate, updatedCheckoutDate, updatedAdditionalneeds).toJSONString())
                .put("booking/" + bookingId);
    }

    /**
     * delete Booking
     *
     * @param bookingId int value to choose which booking you want to delete
     * @return delete Booking
     */
    @Step("delete Booking bookingId:[{bookingId}]")
    public Response deleteBooking(int bookingId) {
        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .delete("booking/" + bookingId);
    }


}
