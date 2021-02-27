import helper.Filter;
import jsonplaceholder.CommentResource;
import jsonplaceholder.PostResource;
import jsonplaceholder.UserResource;
import objectmodels.Comment;
import objectmodels.Post;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class GetPostCommentsTest {

    @Test
    public void validateAllCommentsEmailsMatchFormat() {
        List<Comment> allCommentsPublished = CommentResource.getComments();
        Assert.assertTrue("Not all comments have valid email addresses ",
                Filter.areCommentEmailsMatchingRegex(allCommentsPublished));
    }

    @Test
    public void commentEndpointCouldBeQueriedToGetCommentFromAnEmail() {
        Post postWrittenByTestUser = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId()).get(0);
        Comment commentFromAPost = PostResource.getPostComments(postWrittenByTestUser.getId()).get(0);
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("email", commentFromAPost.getEmail());
        List<Comment> actualComments = CommentResource.getComments(queryParameters);
        Assert.assertEquals(Filter.filterCommentsByEmail(CommentResource.getComments(), commentFromAPost.getEmail()).size(),
                actualComments.size());
    }

    @Test
    public void commentEndpointCouldReturnACommentByItsId(){
        Post postWrittenByTestUser = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId()).get(0);
        Comment commentFromAPost = PostResource.getPostComments(postWrittenByTestUser.getId()).get(0);

        Comment actualComment = CommentResource.getCommentById(commentFromAPost.getId());
        Assert.assertEquals(
                actualComment.getPostId(), postWrittenByTestUser.getId());
    }

    @Test
    public void validateCommentIdCouldNotBeDuplicated() {
        List<Comment> allComments = CommentResource.getComments();
        Set<Integer> idsWithoutDuplicates = new HashSet<>(Filter.filterCommentIds(CommentResource.getComments()));
        Assert.assertEquals(idsWithoutDuplicates.size(), allComments.size());
    }

    @Test
    public void validateCommentBodiesCouldNotBeEmpty() {
        Assert.assertFalse("Comment body could be empty",
                Filter.areCommmentBodiesEmpty(CommentResource.getComments()));
    }

    @Test
    public void validateCommentNamesCouldNotBeEmpty() {
        Assert.assertFalse("Comment name could be empty",
                Filter.areCommentNamesEmpty(CommentResource.getComments()));
    }
}
