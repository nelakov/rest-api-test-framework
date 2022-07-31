package models;

import com.fasterxml.jackson.annotation.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "isSuccess",
        "errorCode",
        "errorMessage",
        "user"
})

public class GetUserResponse {

    @JsonProperty("isSuccess")
    private Boolean isSuccess;
    @JsonProperty("errorCode")
    private Integer errorCode;
    @JsonProperty("errorMessage")
    private Object errorMessage;
    @JsonProperty("user")
    private User user;

    @JsonProperty("isSuccess")
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    @JsonProperty("isSuccess")
    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    @JsonProperty("errorCode")
    public Integer getErrorCode() {
        return errorCode;
    }

    @JsonProperty("errorCode")
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("errorMessage")
    public Object getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("errorMessage")
    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "name",
            "gender",
            "age",
            "city",
            "registrationDate"
    })

    public class User {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("gender")
        private String gender;
        @JsonProperty("age")
        private Integer age;
        @JsonProperty("city")
        private String city;
        @JsonProperty("registrationDate")
        private String registrationDate;

        @JsonProperty("id")
        public Integer getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Integer id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("gender")
        public String getGender() {
            return gender;
        }

        @JsonProperty("gender")
        public void setGender(String gender) {
            this.gender = gender;
        }

        @JsonProperty("age")
        public Integer getAge() {
            return age;
        }

        @JsonProperty("age")
        public void setAge(Integer age) {
            this.age = age;
        }

        @JsonProperty("city")
        public String getCity() {
            return city;
        }

        @JsonProperty("city")
        public void setCity(String city) {
            this.city = city;
        }

        @JsonProperty("registrationDate")
        public String getRegistrationDate() {
            return registrationDate;
        }

        @JsonProperty("registrationDate")
        public void setRegistrationDate(String registrationDate) {
            this.registrationDate = registrationDate;
        }

    }
}