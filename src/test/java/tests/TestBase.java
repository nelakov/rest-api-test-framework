package tests;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import listeners.CustomAllureListener;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.util.Properties;

import static io.restassured.http.ContentType.JSON;

public class TestBase {

    static Properties prop = new Properties();
    static RequestSpecification reqSpecForGetUser = null;
    static RequestSpecification reqSpecForGetUserList = null;
    static ResponseSpecification respSpecForGetUser = null;
    static ResponseSpecification respSpecForUserListPositive = null;
    static ResponseSpecification respSpecCommonForError = null;


    @BeforeAll
    static void beforeAll() {

        reqSpecForGetUser = new RequestSpecBuilder()
                .setBaseUri("https://hr-challenge.interactivestandard.com")
                .setBasePath("api/test/user")
                .setContentType(JSON)
                .addFilter(CustomAllureListener.withCustomTemplates())
                .build();

        reqSpecForGetUserList = new RequestSpecBuilder()
                .setBaseUri("https://hr-challenge.interactivestandard.com")
                .setBasePath("api/test/users")
                .setContentType(JSON)
                .addFilter(CustomAllureListener.withCustomTemplates())
                .build();


        respSpecForGetUser = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();

        respSpecForUserListPositive = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();

        respSpecCommonForError = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectStatusLine("HTTP/1.1 400 Bad Request")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();


    }
}