package models;

import com.fasterxml.jackson.annotation.*;
import java.util.List;
@JsonPropertyOrder({
        "errorCode",
        "isSuccess",
        "errorMessage"
})

public class GetUserListResponse {
    @JsonProperty("isSuccess")
    private Boolean isSuccess;
    @JsonProperty("errorCode")
    private Integer errorCode;
    @JsonProperty("errorMessage")
    private Object errorMessage;
    @JsonProperty("idList")
    private List<Integer> idList = null;

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

    @JsonProperty("idList")
    public List<Integer> getIdList() {
        return idList;
    }

    @JsonProperty("idList")
    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

}