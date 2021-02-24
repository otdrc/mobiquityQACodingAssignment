package jsonPlaceholder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import parser.ConfigParser;

public abstract class JsonPlaceholder {
    protected static final String BASEURL = ConfigParser.getValue("BaseUrl");
    protected RequestSpecification requestSpecification;

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
    public JsonPlaceholder() {
        this.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASEURL)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
