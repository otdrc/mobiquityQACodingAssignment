import helper.Filter;
import jsonPlaceholder.PostResource;
import jsonPlaceholder.UserResource;
import objectModels.Comment;
import objectModels.Post;
import objectModels.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class GetPostsPublishedByUserTest {

    @Test
    public void validatePostsSourceAlreadyHasPublishedData() {
        List<Post> posts = PostResource.getAllPosts();
        Assert.assertTrue(String.format("Posts endpoint does not return the expected number of posts = [%d]", posts.size()),
                posts.size() > 0);
    }

    @Test
    public void allExistingUserPostsCouldBeFoundByUserId() {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = UserResource.getUsers(queryParameters).get(0);
        Assert.assertFalse("Expected User posts are not returned by User id as expected",
                PostResource.getUserPosts(testUser.getId()).isEmpty());
    }

    @Test
    public void allExistingUserPostsCouldBeQueriedByUserId() {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = UserResource.getUsers(queryParameters).get(0);
        queryParameters.put("userId", testUser.getId());
        List<Post> expectedPosts = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId());

        Assert.assertEquals(expectedPosts.size(), PostResource.getAllPosts(queryParameters).size());
    }

    @Test
    public void postEndpointReturnsRequiredPostDetailsByItsId() {
        List<Post> userPosts = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId());
        Post userPost = PostResource.getPostById(userPosts.get(0).getId());
        Assert.assertNotNull(userPost);
    }

    @Test
    public void postsEndpointCouldReturnCommentsUnderAParticularPost() {
        Post post = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId()).get(0);
        List<Comment> comments = PostResource.getPostComments(post.getId());
        Assert.assertTrue(String.format("Comments posted under postId: [%d]", post.getId()),
                comments.size() > 0);
    }

    @Test
    public void validateUserPostsHasCommentsUnderItsPosts() {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = UserResource.getUsers(queryParameters).get(0);
        List<Post> commentUnderUserPosts = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId());
        Assert.assertTrue(String.format("User: [%s] has commented posts", testUser.getUsername()),
                commentUnderUserPosts.size() > 0);
    }
}
