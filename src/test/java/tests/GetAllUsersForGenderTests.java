package tests;

import io.qameta.allure.Story;
import models.CommonResponseError;
import models.GetUserListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAllUsersForGenderTests extends TestBase {

    @Story("Positive Tests")
    @ValueSource(strings = {"male", "female", "any"})
    @ParameterizedTest(name = "Get all users for the gender valid value \"{0}\"")
    void getAllUsersForGenderPositiveTest(String gender) {
        GetUserListResponse allUsers = getUsersByGender(respSpecForUserListPositive, GetUserListResponse.class, gender);

        assertThat(allUsers.getErrorCode()).isEqualTo(0);
        assertThat(allUsers.getIsSuccess()).isTrue();
        assertThat(allUsers.getIdList()).isNotEmpty();
    }

    @Story("Negative Tests")
    @ValueSource(strings = {"tempor cupidatat labore ea", "мужик", "", "null"})
    @ParameterizedTest(name = "Get all users for the gender invalid value \"{0}\"")
    void getAllUsersForGenderNegativeTest(String gender) {
        CommonResponseError allUsersListError = getUsersByGender(respSpecCommonForError, CommonResponseError.class, gender);

        assertThat(allUsersListError.getError()).isEqualTo("Bad Request");
        assertThat(allUsersListError.getStatus()).isEqualTo(400);
        assertThat(allUsersListError.getMessage()).isEqualTo("No enum constant com.coolrocket.app.api.test.qa.Gender.null");
        assertThat(allUsersListError.getPath()).isEqualTo("/api/test/users");
    }

    @Story("Negative Tests")
    @DisplayName("Get all users without the gender parameter")
    @Test
    void getAllUsersWithoutTheGenderParameter() {
        CommonResponseError userListResponseError = getUsersByGender(respSpecCommonForError, CommonResponseError.class, null);

        assertThat(userListResponseError.getError()).isEqualTo("Bad Request");
        assertThat(userListResponseError.getStatus()).isEqualTo(400);
        assertThat(userListResponseError.getMessage()).isEqualTo("Required String parameter 'gender' is not present");
        assertThat(userListResponseError.getPath()).isEqualTo("/api/test/users");
    }
}
