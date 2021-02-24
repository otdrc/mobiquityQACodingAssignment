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
        PostResource postResource = new PostResource();
        Post[] posts = postResource.getAllPosts();
        Assert.assertTrue(String.format("Posts endpoint does not return the expected number of posts = [%d]", posts.length),
                posts.length > 0);
    }

    @Test
    public void allExistingUserPostsCouldBeFoundByUserId() {
        PostResource postResource = new PostResource();
        UserResource userResource = new UserResource();
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = userResource.getUsers(queryParameters)[0];
        Post[] allUserPosts = postResource.getUserPosts(testUser.getId());
        Assert.assertTrue("Expected User posts are not returned by User id as expected", allUserPosts.length > 0);
    }

    @Test
    public void allExistingUserPostsCouldBeQueriedByUserId() {
        PostResource postResource = new PostResource();
        UserResource userResource = new UserResource();
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = userResource.getUsers(queryParameters)[0];
        queryParameters.put("userId", testUser.getId());
        Post[] actualPosts = postResource.getAllPosts(queryParameters);
        Post[] expectedPosts = userResource
                .getUserPosts(Filter.filterUsersByName(userResource.getUsers(), "Delphine").getId());

        Assert.assertTrue("All existing posts are not filtered by a username parameter",
                actualPosts.length == expectedPosts.length);
    }

    @Test
    public void postEndpointReturnsRequiredPostDetailsByItsId() {
        PostResource postResource = new PostResource();
        UserResource userResource = new UserResource();
        Post[] userPosts = userResource
                .getUserPosts(Filter.filterUsersByName(userResource.getUsers(), "Delphine").getId());
        Post userPost = postResource.getPostById(userPosts[0].getId());
        Assert.assertNotNull(userPost);
    }

    @Test
    public void postsEndpointCouldReturnCommentsUnderAParticularPost(){
        PostResource postResource = new PostResource();
        UserResource userResource = new UserResource();
        Post post = userResource
                .getUserPosts(Filter.filterUsersByName(userResource.getUsers(), "Delphine").getId())[0];
        Comment[] comments = postResource.getPostComments(post.getId());
        Assert.assertTrue(String.format("Comments posted under postId: [%d]", post.getId()),
                comments.length > 0);
    }

    @Test
    public void validateUserPostsHasCommentsUnderItsPosts(){
        PostResource postResource = new PostResource();
        UserResource userResource = new UserResource();
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = userResource.getUsers(queryParameters)[0];
        Post[] allPostsUserHas = userResource
                .getUserPosts(Filter.filterUsersByName(userResource.getUsers(), "Delphine").getId());
        List<Comment> commentUnderUserPosts = new ArrayList<>();
        for (Post post : allPostsUserHas) {
            commentUnderUserPosts.addAll(Arrays.asList(postResource.getPostComments(post.getId())));
        }
        Assert.assertTrue(String.format("User: [%s] has commented posts", testUser.getUsername()),
                commentUnderUserPosts.size() > 0);
    }
}
