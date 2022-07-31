package tests;

import io.qameta.allure.Story;
import models.GetUserListResponse;
import models.CommonResponseError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class GetAllUsersForGenderTests extends TestBase {

    @Story("Positive Tests")
    @ValueSource(strings = {"male", "female", "any"})
    @ParameterizedTest(name = "Get all users for the gender valid value \"{0}\"")
    void getAllUsersForGenderPositiveTest(String gender) {
        GetUserListResponse allUsers = given()
                .spec(reqSpecForGetUserList)
                .param("gender", gender)
                .log()
                .all()
                .when()
                .get()
                .then()
                .log().all()
                .spec(respSpecForUserListPositive)
                .body(matchesJsonSchemaInClasspath("schemas/schemaV3.json"))
                .extract().as(GetUserListResponse.class);

        assertThat(allUsers.getErrorCode()).isEqualTo(0);
        assertThat(allUsers.getIsSuccess()).isEqualTo(true);
        assertThat(allUsers.getIdList()).isNotEmpty();
    }

    @Story("Negative Tests")
    @ValueSource(strings = {"tempor cupidatat labore ea", "мужик", "", "null"})
    @ParameterizedTest(name = "Get all users for the gender invalid value \"{0}\"")
    void getAllUsersForGenderNegativeTest(String gender) {
        CommonResponseError allUsersListError = given()
                .spec(reqSpecForGetUserList)
                .param("gender", gender)
                .log()
                .all()
                .when()
                .get()
                .then()
                .log().all()
                .spec(respSpecCommonForError)
                .body(matchesJsonSchemaInClasspath("schemas/schemaV3.json"))
                .extract().as(CommonResponseError.class);

        assertThat(allUsersListError.getError()).isEqualTo("Bad Request");
        assertThat(allUsersListError.getStatus()).isEqualTo(400);
        assertThat(allUsersListError.getMessage()).isEqualTo("No enum constant com.coolrocket.app.api.test.qa.Gender.null");
        assertThat(allUsersListError.getPath()).isEqualTo("/api/test/users");

    }

    @Story("Negative Tests")
    @DisplayName("Get all users without the gender parameter")
    @Test
    void getAllUsersWithoutTheGenderParameter() {
        CommonResponseError userListResponseError =
                given()
                        .spec(reqSpecForGetUserList)
                        .log()
                        .all()
                        .when()
                        .get()
                        .then()
                        .log().all()
                        .spec(respSpecCommonForError)
                        .body(matchesJsonSchemaInClasspath("schemas/schemaV3.json"))
                        .extract().as(CommonResponseError.class);

        assertThat(userListResponseError.getError()).isEqualTo("Bad Request");
        assertThat(userListResponseError.getStatus()).isEqualTo(400);
        assertThat(userListResponseError.getMessage()).isEqualTo("Required String parameter 'gender' is not present");
        assertThat(userListResponseError.getPath()).isEqualTo("/api/test/users");

    }

}
