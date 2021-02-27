package jsonPlaceholder;

import objectModels.Comment;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CommentResource extends JsonPlaceholder {

    public static Comment[] getComments() {
        Comment[] allComments = given()
                .spec(requestSpecification)
                .get(Endpoint.COMMENTS)
                .body()
                .as(Comment[].class);
        return allComments;
    }

    public static Comment[] getComments(Map<String, Object> queryParameters) {
        Comment[] comments = given()
                .spec(requestSpecification)
                .queryParams(queryParameters)
                .get(Endpoint.COMMENTS)
                .body()
                .as(Comment[].class);
        return comments;
    }

    public static Comment getCommentById(int commentId) {
        Comment comment = given()
                .spec(requestSpecification)
                .get(Endpoint.COMMENT_BY_ID, commentId)
                .body()
                .as(Comment.class);
        return comment;
    }
}
