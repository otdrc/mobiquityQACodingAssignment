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
        Post[] posts = PostResource.getAllPosts();
        Assert.assertTrue(String.format("Posts endpoint does not return the expected number of posts = [%d]", posts.length),
                posts.length > 0);
    }

    @Test
    public void allExistingUserPostsCouldBeFoundByUserId() {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = UserResource.getUsers(queryParameters)[0];
        Post[] allUserPosts = PostResource.getUserPosts(testUser.getId());
        Assert.assertTrue("Expected User posts are not returned by User id as expected", allUserPosts.length > 0);
    }

    @Test
    public void allExistingUserPostsCouldBeQueriedByUserId() {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = UserResource.getUsers(queryParameters)[0];
        queryParameters.put("userId", testUser.getId());
        Post[] actualPosts = PostResource.getAllPosts(queryParameters);
        Post[] expectedPosts = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId());

        Assert.assertEquals(actualPosts.length, expectedPosts.length);
    }

    @Test
    public void postEndpointReturnsRequiredPostDetailsByItsId() {
        Post[] userPosts = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId());
        Post userPost = PostResource.getPostById(userPosts[0].getId());
        Assert.assertNotNull(userPost);
    }

    @Test
    public void postsEndpointCouldReturnCommentsUnderAParticularPost(){
        Post post = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId())[0];
        Comment[] comments = PostResource.getPostComments(post.getId());
        Assert.assertTrue(String.format("Comments posted under postId: [%d]", post.getId()),
                comments.length > 0);
    }

    @Test
    public void validateUserPostsHasCommentsUnderItsPosts(){
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User testUser = UserResource.getUsers(queryParameters)[0];
        Post[] allPostsUserHas = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId());
        List<Comment> commentUnderUserPosts = new ArrayList<>();
        for (Post post : allPostsUserHas) {
            commentUnderUserPosts.addAll(Arrays.asList(PostResource.getPostComments(post.getId())));
        }
        Assert.assertTrue(String.format("User: [%s] has commented posts", testUser.getUsername()),
                commentUnderUserPosts.size() > 0);
    }
}
