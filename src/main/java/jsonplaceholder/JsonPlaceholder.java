package jsonplaceholder;

import configuration.TestConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class JsonPlaceholder {
    protected static final String BASEURL = TestConfig.getInstance().getBaseUrl();

    protected static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASEURL)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private JsonPlaceholder() {
    }
}
