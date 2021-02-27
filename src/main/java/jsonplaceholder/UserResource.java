package jsonplaceholder;

import objectmodels.Post;
import objectmodels.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserResource extends JsonPlaceholder {
    UserResource() {}

    public static List<User> getUsers() {
        return Arrays.asList(
                given()
                        .spec(requestSpecification)
                        .get(Endpoint.USERS)
                        .body()
                        .as(User[].class)
        );
    }

    public static List<User> getUsers(Map<String, Object> queryParameters) {
        return Arrays.asList(
                given()
                        .spec(requestSpecification)
                        .queryParams(queryParameters)
                        .get(Endpoint.USERS)
                        .body()
                        .as(User[].class)
        );
    }

    public static User getUserById(int userId) {
        return given()
                .spec(requestSpecification)
                .get(Endpoint.USER_BY_ID, userId)
                .body()
                .as(User.class);
    }

    public static List<Post> getUserPosts(int userId) {
        return Arrays.asList(
                given()
                        .spec(requestSpecification)
                        .get(Endpoint.POSTS_BY_USER_ID, userId)
                        .body()
                        .as(Post[].class)
        );
    }
}
