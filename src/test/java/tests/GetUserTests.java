package tests;

import io.qameta.allure.Story;
import models.CommonResponseError;
import models.GetUserListResponse;
import models.GetUserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class GetUserTests extends TestBase {

    @Story("Positive Tests")
    @ValueSource(ints = {5, 15, 16, 300, 502, 503})
    @ParameterizedTest(name = "Get user for the female id list \"{0}\"")
    void getUserForFemaleIdList(int id) {
        GetUserResponse getUserResponse =
                given()
                        .spec(reqSpecForGetUser)
                        .log()
                        .all()
                        .when()
                        .get("{id}", id)
                        .then()
                        .log().all()
                        .spec(respSpecForGetUser)
                        .body(matchesJsonSchemaInClasspath("schemas/schemaV3.json"))
                        .extract().as(GetUserResponse.class);

        assertThat(getUserResponse.getIsSuccess()).isTrue();
        assertThat(getUserResponse.getErrorCode()).isEqualTo(0);
        assertThat(getUserResponse.getUser().getAge()).isNotNull();
        assertThat(getUserResponse.getUser().getCity()).isNotEmpty();
        assertThat(getUserResponse.getUser().getGender()).isEqualTo("female");
        assertThat(getUserResponse.getUser().getId()).isNotNull();
        assertThat(getUserResponse.getUser().getName()).isNotEmpty();
        assertThat(getUserResponse.getUser().getRegistrationDate()).isNotEmpty();

    }

    @Story("Positive Tests")
    @ValueSource(ints = {10, 15, 33, 94, 501, 911})
    @ParameterizedTest(name = "Get user for the male id list \"{0}\"")
    void getUserForMaleIdList(int id) {
        GetUserResponse getUserResponse =
                given()
                        .spec(reqSpecForGetUser)
                        .log()
                        .all()
                        .when()
                        .get("{id}", id)
                        .then()
                        .log().all()
                        .spec(respSpecForGetUser)
                        .body(matchesJsonSchemaInClasspath("schemas/schemaV3.json"))
                        .extract().as(GetUserResponse.class);

        assertThat(getUserResponse.getIsSuccess()).isTrue();
        assertThat(getUserResponse.getErrorCode()).isEqualTo(0);
        assertThat(getUserResponse.getUser().getAge()).isNotNull();
        assertThat(getUserResponse.getUser().getCity()).isNotEmpty();
        assertThat(getUserResponse.getUser().getGender()).isEqualTo("male");
        assertThat(getUserResponse.getUser().getId()).isNotNull();
        assertThat(getUserResponse.getUser().getName()).isNotEmpty();
        assertThat(getUserResponse.getUser().getRegistrationDate()).isNotEmpty();
    }

    @Story("Positive Tests")
    @ValueSource(ints = {0, 5, 10, 15, 16, 33, 94, 212, 300, 501, 502, 503, 911})
    @ParameterizedTest(name = "Get user for the any id list \"{0}\"")
    void getUserForAnyIdList(int id) {
        GetUserResponse getUserResponse =
                given()
                        .spec(reqSpecForGetUser)
                        .log()
                        .all()
                        .when()
                        .get("{id}", id)
                        .then()
                        .log().all()
                        .spec(respSpecForGetUser)
                        .body(matchesJsonSchemaInClasspath("schemas/schemaV3.json"))
                        .extract().as(GetUserResponse.class);

        assertThat(getUserResponse.getIsSuccess()).isTrue();
        assertThat(getUserResponse.getErrorCode()).isEqualTo(0);
        assertThat(getUserResponse.getUser().getAge()).isNotNull();
        assertThat(getUserResponse.getUser().getCity()).isNotEmpty();
        assertThat(getUserResponse.getUser().getGender()).isNotEqualTo("male");
        assertThat(getUserResponse.getUser().getGender()).isNotEqualTo("female");
        assertThat(getUserResponse.getUser().getId()).isNotNull();
        assertThat(getUserResponse.getUser().getName()).isNotEmpty();
        assertThat(getUserResponse.getUser().getRegistrationDate()).isNotEmpty();

    }

    @Story("Negative Tests")
    @DisplayName("Get user without id")
    @Test
    void getUserWithoutId() {
        CommonResponseError response =
                given()
                        .spec(reqSpecForGetUser)
                        .log()
                        .all()
                        .when()
                        .get()
                        .then()
                        .log().all()
                        .spec(respSpecCommonForError)
                        .body(matchesJsonSchemaInClasspath("schemas/schemaV3.json"))
                        .extract().as(CommonResponseError.class);

        assertThat(response.getError()).isEqualTo("Not Found");
        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.getMessage()).isEqualTo("No message available");
        assertThat(response.getPath()).isEqualTo("/api/test/user");
    }

    @Story("Negative Tests")
    @ValueSource(ints = {1001, 100})
    @ParameterizedTest(name = "Get user with non - existent id \"{0}\"")
    void getUserWithNonExistId(int id) {
        GetUserResponse response =
                given()
                        .spec(reqSpecForGetUser)
                        .log()
                        .all()
                        .when()
                        .get("{id}", id)
                        .then()
                        .log().all()
                        .spec(respSpecCommonForError)
                        .body(matchesJsonSchemaInClasspath("schemas/schemaV3.json"))
                        .extract().as(GetUserResponse.class);

        assertThat(response.getIsSuccess()).isFalse();
        assertThat(response.getErrorCode()).isEqualTo(400);
    }

    @Story("Negative Tests")
    @ValueSource(strings = {"!", "'", "#", "-", "null"})
    @ParameterizedTest(name = "Send special character instead id \"{0}\"")
    void getUserWithAnyCharacter(String id) {
        GetUserResponse response =
                given()
                        .spec(reqSpecForGetUser)
                        .log()
                        .all()
                        .when()
                        .get("{id}", id)
                        .then()
                        .log().all()
                        .spec(respSpecCommonForError)
                        .body(matchesJsonSchemaInClasspath("schemas/schemaV3.json"))
                        .extract().as(GetUserResponse.class);

        assertThat(response.getIsSuccess()).isFalse();
        assertThat(response.getErrorCode()).isEqualTo(400);
        assertThat(response.getErrorMessage()).toString().contains("NumberFormatException: For input string: ");

    }

}
