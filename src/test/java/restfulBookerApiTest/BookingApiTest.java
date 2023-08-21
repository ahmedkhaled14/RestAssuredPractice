package restfulBookerApiTest;

import RestfulBooker.Booking;
import Utils.JsonFileManager;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


@Epic("Restful Booker API Test")
@Feature("Verify CRUD Operations on Bookings module")
public class BookingApiTest {

    Booking booking ;
    JsonFileManager createBookingJson;
    JsonFileManager updateBookingJson;
    JsonFileManager loginJson;
    @BeforeMethod
   public void beforeMethod(){
        booking = new Booking();
        createBookingJson  = new JsonFileManager("src/test/resources/TestData/BookingsTestDate/createBooking.json");
        updateBookingJson  = new JsonFileManager("src/test/resources/TestData/BookingsTestDate/updateBooking.json");
        loginJson = new JsonFileManager("src/test/resources/TestData/BookingsTestDate/loginBooking.json");

   }
    @Test(description = "authentication")
    @Story("post Request")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description : Verify authentication token")
    public void login() {
        String token = booking.authentication
                        (
                                loginJson.getTestData("userName"),
                                loginJson.getTestData("password")
                        )
                .jsonPath().get("token");
        System.out.println(token);
    }


    @Test(description = "get all Bookings Ids")
    @Story("GET Request")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description : Verify status Code equal:200")
    public void getAllBookingsIds() {
        booking.getBookingIds()
                .then()
                .statusCode(200)
                .log()
                .all();
    }


    @Test(description = "get Booking By ID")
    @Story("GET Request")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description :  Verify status Code equal:200 ")
    public void getBooking() {
        booking.getSpecificBooking(2)
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test(description = "create New Booking")
    @Story("post Request")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description :  Verify status Code equal:200 & Verify firstname & lastname of new Booking")
    public void postBooking() {
        booking
                .createBooking
                        (
                                createBookingJson.getTestData("firstname"),
                                createBookingJson.getTestData("lastname"),
                                Integer.parseInt(createBookingJson.getTestData("totalprice")),
                                Boolean.parseBoolean(createBookingJson.getTestData("depositpaid")),
                                createBookingJson.getTestData("checkin"),
                                createBookingJson.getTestData("checkout"),
                                createBookingJson.getTestData("additionalneeds")
                        )
                .then()
                .assertThat()
                .body("booking.firstname" , equalTo(createBookingJson.getTestData("firstname")))
                .body("booking.lastname" , equalTo(createBookingJson.getTestData("lastname")))
                .statusCode(200)
                .log()
                .all();
    }

    @Test(description = "update Booking")
    @Story("put Request")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description :  update Booking")
    public void putBooking() {

        int BookingId = booking.getBookingIds().jsonPath().get("[0].bookingid");
        String token = booking.authentication( loginJson.getTestData("userName"),
                loginJson.getTestData("password")).jsonPath().get("token");

        booking
                .updateBooking
                        (
                                token,
                                BookingId,
                                updateBookingJson.getTestData("firstname"),
                                updateBookingJson.getTestData("lastname"),
                                Integer.parseInt(updateBookingJson.getTestData("totalprice")),
                                Boolean.parseBoolean(updateBookingJson.getTestData("depositpaid")),
                                updateBookingJson.getTestData("bookingdates.checkin"),
                                updateBookingJson.getTestData("bookingdates.checkout"),
                                updateBookingJson.getTestData("additionalneeds")

                        )
                .then()
                .log()
                .all();
    }

    @Test(description = "delete Booking")
    @Story("delete Request")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description :  delete Booking")
    public void deleteBooking() {
        int BookingId = booking.getBookingIds().jsonPath().get("[2].bookingid");
        String token = booking.authentication( loginJson.getTestData("userName"),
                loginJson.getTestData("password")).jsonPath().get("token");        booking
                .deleteBooking(BookingId,token)
                .then()
                .log()
                .all();
    }

}
