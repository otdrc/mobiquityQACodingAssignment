package jsonplaceholder;

import objectmodels.Comment;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CommentResource {
    private CommentResource() {
    }

    public static List<Comment> getComments() {
        return Arrays.asList(
                given()
                        .spec(JsonPlaceholder.requestSpecification)
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
                        .spec(JsonPlaceholder.requestSpecification)
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
                .spec(JsonPlaceholder.requestSpecification)
                .get(Endpoint.COMMENT_BY_ID, commentId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Comment.class);
    }

    public static Integer postComment(Comment comment) {
        return given()
                .spec(JsonPlaceholder.requestSpecification)
                .body(comment)
                .when()
                .post(Endpoint.COMMENTS)
                .then()
                .extract()
                .statusCode();
    }
}
