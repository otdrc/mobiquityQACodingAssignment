package jsonplaceholder;

import objectmodels.Post;
import objectmodels.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserResource {
    private UserResource() {
    }

    public static List<User> getUsers() {
        return Arrays.asList(
                given()
                        .spec(JsonPlaceholder.requestSpecification)
                        .get(Endpoint.USERS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(User[].class)
        );
    }

    public static List<User> getUsers(Map<String, Object> queryParameters) {
        return Arrays.asList(
                given()
                        .spec(JsonPlaceholder.requestSpecification)
                        .queryParams(queryParameters)
                        .get(Endpoint.USERS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(User[].class)
        );
    }

    public static User getUserById(int userId) {
        return given()
                .spec(JsonPlaceholder.requestSpecification)
                .get(Endpoint.USER_BY_ID, userId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(User.class);
    }

    public static List<Post> getUserPosts(int userId) {
        return Arrays.asList(
                given()
                        .spec(JsonPlaceholder.requestSpecification)
                        .get(Endpoint.POSTS_BY_USER_ID, userId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(Post[].class)
        );
    }
}
