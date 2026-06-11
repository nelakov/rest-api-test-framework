package listeners;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomAllureListener {

    private static final AllureRestAssured FILTER = createFilter();

    private static AllureRestAssured createFilter() {
        AllureRestAssured filter = new AllureRestAssured();
        filter.setRequestTemplate("request.ftl");
        filter.setResponseTemplate("response.ftl");
        return filter;
    }

    public static AllureRestAssured withCustomTemplates() {
        return FILTER;
    }

}
