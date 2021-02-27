package jsonplaceholder;

import objectmodels.Comment;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CommentResource extends JsonPlaceholder {
    private CommentResource() {}

    public static List<Comment> getComments() {
        return Arrays.asList(
                given()
                        .spec(requestSpecification)
                        .get(Endpoint.COMMENTS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(Comment[].class)
        );
    }

    public static List<Comment> getComments(Map<String, Object> queryParameters) {
        return Arrays.asList(
                given()
                        .spec(requestSpecification)
                        .queryParams(queryParameters)
                        .get(Endpoint.COMMENTS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(Comment[].class)
        );
    }

    public static Comment getCommentById(int commentId) {
        return given()
                .spec(requestSpecification)
                .get(Endpoint.COMMENT_BY_ID, commentId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Comment.class);
    }
}
