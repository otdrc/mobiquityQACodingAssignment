package jsonPlaceholder;

import objectModels.Post;
import objectModels.User;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserResource extends JsonPlaceholder {

    public static User[] getUsers() {
        User[] allUsers = given()
                .spec(requestSpecification)
                .get(Endpoint.USERS)
                .body()
                .as(User[].class);
        return allUsers;
    }

    public static User[] getUsers(Map<String, Object> queryParameters) {
        User [] users = given()
                .spec(requestSpecification)
                .queryParams(queryParameters)
                .get(Endpoint.USERS)
                .body()
                .as(User[].class);
        return users;
    }

    public static User getUserById(int userId) {
        User user = given()
                .spec(requestSpecification)
                .get(Endpoint.USER_BY_ID, userId)
                .body()
                .as(User.class);
        return user;
    }

    public static Post[] getUserPosts(int userId) {
        Post[] posts = given()
                .spec(requestSpecification)
                .get(Endpoint.POSTS_BY_USER_ID, userId)
                .body()
                .as(Post[].class);

        return posts;
    }
}
