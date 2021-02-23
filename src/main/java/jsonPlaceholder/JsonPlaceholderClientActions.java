package jsonPlaceholder;

import io.restassured.http.ContentType;
import objectModels.UserResource;
import parser.ConfigParser;

import static io.restassured.RestAssured.given;

public class JsonPlaceholderClientActions {
    private static final String BASE_URL = ConfigParser.getValue("BaseUrl");

    public UserResource getUserByName(String username) {
        UserResource[] allUsers = given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get(Endpoint.USERS)
                .body()
                .as(UserResource[].class);

        UserResource user = new UserResource();
        for (UserResource currentUser : allUsers) {
            if (currentUser.getUsername().equals(username)) {
                user = currentUser;
                break;
            }
        }
        return user;
    }
}
