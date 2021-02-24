import helper.Helper;
import jsonPlaceholder.PostResource;
import objectModels.Comment;
import objectModels.Post;
import objectModels.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class GetPostsPublishedByUser {

    @Test
    public void validatePostsSourceAlreadyHasPublishedData() {
        PostResource postResource = new PostResource();
        Post[] posts = postResource.getAllPosts();
        Assert.assertTrue(String.format("Validate posts endpoint return the following number of posts = [%d]", posts.length),
                posts.length > 0);
    }

    @Test
    public void allExistingUserPostsCouldBeFoundByUserId() {
        PostResource postResource = new PostResource();
        User testUser = Helper.getUserByUsername("Delphine");
        Post[] allUserPosts = postResource.getUserPosts(testUser.getId());
        Assert.assertTrue("Expected User already has a few posts published", allUserPosts.length > 0);
    }

    @Test
    public void allExistingUserPostsCouldBeQueriedByUserId() {
        PostResource postResource = new PostResource();
        User testUser = Helper.getUserByUsername("Delphine");
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("userId", testUser.getId());
        Post[] actualPosts = postResource.getAllPosts(queryParameters);
        Post[] expectedPosts = Helper.getAllPostsUserHas(String.valueOf(testUser.getUsername()));

        Assert.assertTrue("All existing posts could be filtered by a username parameter",
                actualPosts.length == expectedPosts.length);
    }

    @Test
    public void postEndpointReturnsRequiredPostDetailsByItsId() {
        PostResource postResource = new PostResource();
        User testUser = Helper.getUserByUsername("Delphine");
        Post[] userPosts = Helper.getAllPostsUserHas(testUser.getUsername());
        Post userPost = postResource.getPostById(userPosts[0].getId());
        Assert.assertNotNull(userPost);
    }

    @Test
    public void postsEndpointCouldReturnCommentsUnderAParticularPost(){
        //to be fulfilled with create data
        PostResource postResource = new PostResource();
        Post post = Helper.getAllPostsUserHas("Delphine")[0];
        Comment[] comments = postResource.getPostCommets(post.getId());
        Assert.assertTrue(String.format("Comments posted under postId: [%d]", post.getId()),
                comments.length > 0);
    }

    @Test
    public void validateUserPostsHasCommentsUnderItsPosts(){
        PostResource postResource = new PostResource();
        User testUser = Helper.getUserByUsername("Delphine");
        Post[] allPostsUserHas = Helper.getAllPostsUserHas(testUser.getUsername());
        List<Comment> commentUnderUserPosts = new ArrayList<>();
        for (Post post : allPostsUserHas) {
            commentUnderUserPosts.addAll(Arrays.asList(postResource.getPostCommets(post.getId())));
        }
        Assert.assertTrue(String.format("User: [%s] has commented posts", testUser.getUsername()),
                commentUnderUserPosts.size() > 0);
    }
}
