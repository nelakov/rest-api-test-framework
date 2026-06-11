package tests;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import listeners.CustomAllureListener;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.http.ContentType.JSON;

public class TestBase {

    private static final String BASE_URI = "https://hr-challenge.interactivestandard.com";

    static RequestSpecification reqSpecForGetUser = null;
    static RequestSpecification reqSpecForGetUserList = null;
    static ResponseSpecification respSpecForGetUser = null;
    static ResponseSpecification respSpecForUserListPositive = null;
    static ResponseSpecification respSpecCommonForError = null;

    private static RequestSpecification reqSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(basePath)
                .setContentType(JSON)
                .addFilter(CustomAllureListener.withCustomTemplates())
                .build();
    }

    @BeforeAll
    static void beforeAll() {

        reqSpecForGetUser = reqSpec("api/test/user");
        reqSpecForGetUserList = reqSpec("api/test/users");

        respSpecForGetUser = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
        respSpecForUserListPositive = respSpecForGetUser;

        respSpecCommonForError = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectStatusLine("HTTP/1.1 400 Bad Request")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();

    }
}
