package jsonplaceholder;

import objectmodels.Comment;
import objectmodels.Post;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostResource extends JsonPlaceholder {
    private PostResource() {}

    public static List<Post> getAllPosts() {
        return Arrays.asList(
                given().
                        spec(requestSpecification)
                        .get(Endpoint.POSTS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(Post[].class)
        );
    }

    public static List<Post> getAllPosts(Map<String, Object> queryParameters) {
        return Arrays.asList(
                given()
                        .spec(requestSpecification)
                        .queryParams(queryParameters)
                        .get(Endpoint.POSTS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(Post[].class)
        );
    }

    public static Post getPostById(int postId) {
        return given()
                .spec(requestSpecification)
                .get(Endpoint.POST_BY_ID, postId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Post.class);
    }

    public static List<Post> getUserPosts(int userId) {
        return Arrays.asList(
                given()
                        .spec(requestSpecification)
                        .get(Endpoint.POSTS_BY_USER_ID, userId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(Post[].class)
        );
    }

    public static List<Comment> getPostComments(int postId) {
        return Arrays.asList(
                given()
                        .spec(requestSpecification)
                        .get(Endpoint.COMMENTS_FROM_POST, postId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(Comment[].class)
        );
    }
}
