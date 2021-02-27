package jsonplaceholder;

public final class Endpoint {
    public static final String USERS = "/users";
    public static final String USER_BY_ID = "/users/{id}";
    public static final String POSTS_BY_USER_ID = "/users/{id}/posts";

    public static final String POSTS = "/posts";
    public static final String POST_BY_ID = "/posts/{id}";
    public static final String COMMENTS_FROM_POST = "/posts/{id}/comments";

    public static final String COMMENTS = "/comments";
    public static final String COMMENT_BY_ID = "/comments/{id}";

    private Endpoint() {}
}
