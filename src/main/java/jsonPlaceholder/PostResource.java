package jsonPlaceholder;

import objectModels.Comment;
import objectModels.Post;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostResource extends JsonPlaceholder {
    public PostResource() {
        super();
    }

    public Post[] getAllPosts() {
        Post[] posts = given().
                spec(requestSpecification)
                .get(Endpoint.POSTS)
                .body()
                .as(Post[].class);
        return posts;
    }

    public Post[] getAllPosts(Map<String, Object> queryParameters) {
        Post [] posts = given()
                .spec(requestSpecification)
                .queryParams(queryParameters)
                .get(Endpoint.POSTS)
                .body()
                .as(Post[].class);
        return posts;
    }

    public Post getPostById(int postId) {
        Post post = given()
                .spec(requestSpecification)
                .get(Endpoint.POST_BY_ID, postId)
                .body()
                .as(Post.class);
        return post;
    }

    public Post[] getUserPosts(int userId) {
        Post[] posts = given()
                .spec(requestSpecification)
                .get(Endpoint.POSTS_BY_USER_ID, userId)
                .body()
                .as(Post[].class);

        return posts;
    }

    public Comment[] getPostComments(int postId) {
        Comment[] comments = given()
                .spec(requestSpecification)
                .get(Endpoint.COMMENTS_FROM_POST, postId)
                .body()
                .as(Comment[].class);

        return comments;
    }
}
