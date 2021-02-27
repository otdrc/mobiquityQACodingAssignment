import helper.Filter;
import jsonplaceholder.PostResource;
import jsonplaceholder.UserResource;
import objectmodels.Comment;
import objectmodels.Post;
import objectmodels.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.*;

public class GetPostsPublishedByUserTest {

    @Test
    public void validatePostsSourceAlreadyHasPublishedData() {
        Assertions
                .assertThat(PostResource.getAllPosts())
                .as("Posts endpoint could not return no data")
                .isNotEmpty();
    }

    @Test
    public void allExistingUserPostsCouldBeFoundByUserId() {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = UserResource.getUsers(queryParameters).get(0);
        Assertions
                .assertThat(PostResource.getUserPosts(testUser.getId()))
                .as("User posts should be returned by UserId query parameter")
                .isNotEmpty();
    }

    @Test
    public void allExistingUserPostsCouldBeQueriedByUserId() {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = UserResource.getUsers(queryParameters).get(0);
        queryParameters.put("userId", testUser.getId());
        List<Post> expectedPosts = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId());
        Assertions
                .assertThat(PostResource.getAllPosts(queryParameters))
                .hasSameSizeAs(expectedPosts);
    }

    @Test
    public void postEndpointReturnsRequiredPostDetailsByItsId() {
        List<Post> userPosts = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId());
        Post userPost = PostResource.getPostById(userPosts.get(0).getId());
        Assertions
                .assertThat(userPost)
                .isNotNull();
    }

    @Test
    public void postsEndpointCouldReturnCommentsUnderAParticularPost() {
        Post post = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId()).get(0);
        List<Comment> comments = PostResource.getPostComments(post.getId());
        Assertions
                .assertThat(comments)
                .as("Comments posted under postId: [%d]", post.getId())
                .hasSizeGreaterThan(0);
    }

    @Test
    public void validateUserPostsHasCommentsUnderItsPost() {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = UserResource.getUsers(queryParameters).get(0);
        List<Post> commentsUnderUserPosts = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId());
        Assertions
                .assertThat(commentsUnderUserPosts)
                .as("User: [%s] has commented posts", testUser.getUsername())
                .hasSizeGreaterThan(0);
    }
}
