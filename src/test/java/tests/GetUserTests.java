package tests;

import io.qameta.allure.Story;
import models.CommonResponseError;
import models.GetUserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class GetUserTests extends TestBase {

    @Story("Positive Tests")
    @ValueSource(ints = {5, 15, 16, 300, 502, 503})
    @ParameterizedTest(name = "Get user for the female id list \"{0}\"")
    void getUserForFemaleIdList(int id) {
        GetUserResponse response = getUserById(respSpecForGetUser, GetUserResponse.class, id);

        assertCommonUserFields(response);
        assertThat(response.getUser().getGender()).isEqualTo("female");
    }

    @Story("Positive Tests")
    @ValueSource(ints = {10, 15, 33, 94, 501, 911})
    @ParameterizedTest(name = "Get user for the male id list \"{0}\"")
    void getUserForMaleIdList(int id) {
        GetUserResponse response = getUserById(respSpecForGetUser, GetUserResponse.class, id);

        assertCommonUserFields(response);
        assertThat(response.getUser().getGender()).isEqualTo("male");
    }

    @Story("Positive Tests")
    @ValueSource(ints = {0, 5, 10, 15, 16, 33, 94, 212, 300, 501, 502, 503, 911})
    @ParameterizedTest(name = "Get user for the any id list \"{0}\"")
    void getUserForAnyIdList(int id) {
        GetUserResponse response = getUserById(respSpecForGetUser, GetUserResponse.class, id);

        assertCommonUserFields(response);
        assertThat(response.getUser().getGender()).isNotEqualTo("male");
        assertThat(response.getUser().getGender()).isNotEqualTo("female");
    }

    @Story("Negative Tests")
    @DisplayName("Get user without id")
    @Test
    void getUserWithoutId() {
        CommonResponseError response = getUser(respSpecCommonForError, CommonResponseError.class);

        assertThat(response.getError()).isEqualTo("Not Found");
        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.getMessage()).isEqualTo("No message available");
        assertThat(response.getPath()).isEqualTo("/api/test/user");
    }

    @Story("Negative Tests")
    @ValueSource(ints = {1001, 100})
    @ParameterizedTest(name = "Get user with non - existent id \"{0}\"")
    void getUserWithNonExistId(int id) {
        GetUserResponse response = getUserById(respSpecCommonForError, GetUserResponse.class, id);

        assertThat(response.getIsSuccess()).isFalse();
        assertThat(response.getErrorCode()).isEqualTo(400);
    }

    @Story("Negative Tests")
    @ValueSource(strings = {"!", "'", "#", "-", "null"})
    @ParameterizedTest(name = "Send special character instead id \"{0}\"")
    void getUserWithAnyCharacter(String id) {
        GetUserResponse response = getUserById(respSpecCommonForError, GetUserResponse.class, id);

        assertThat(response.getIsSuccess()).isFalse();
        assertThat(response.getErrorCode()).isEqualTo(400);
        assertThat(response.getErrorMessage()).asString().contains("NumberFormatException: For input string: ");
    }

    private static void assertCommonUserFields(GetUserResponse response) {
        assertThat(response.getIsSuccess()).isTrue();
        assertThat(response.getErrorCode()).isEqualTo(0);
        assertThat(response.getUser().getAge()).isNotNull();
        assertThat(response.getUser().getCity()).isNotEmpty();
        assertThat(response.getUser().getId()).isNotNull();
        assertThat(response.getUser().getName()).isNotEmpty();
        assertThat(response.getUser().getRegistrationDate()).isNotEmpty();
    }
}
