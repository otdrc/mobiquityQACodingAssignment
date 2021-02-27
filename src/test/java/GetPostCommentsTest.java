import helper.Filter;
import jsonPlaceholder.CommentResource;
import jsonPlaceholder.PostResource;
import jsonPlaceholder.UserResource;
import objectModels.Comment;
import objectModels.Post;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetPostCommentsTest {

    @Test
    public void validateAllCommentsEmailsMatchFormat() {
        Pattern validEmailAddressRegex =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        boolean flag = true;
        Comment[] comments = CommentResource.getComments();
        List<Comment> allCommentsPublished = Arrays.asList(comments);
        for (Comment comment : allCommentsPublished) {
            Matcher matcher = validEmailAddressRegex.matcher(comment.getEmail());
            flag = matcher.find();
        }
        Assert.assertTrue("Not all comments have valid email addresses ", flag);
    }

    @Test
    public void commentEndpointCouldBeQueriedToGetCommentFromAnEmail() {
        Post postWrittenByTestUser = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId())[0];
        Comment commentFromAPost = PostResource.getPostComments(postWrittenByTestUser.getId())[0];
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("email", commentFromAPost.getEmail());
        Comment[] allComments = CommentResource.getComments();
        Comment[] actualComments = CommentResource.getComments(queryParameters);
        Assert.assertEquals(actualComments.length,Filter.filterCommentsByEmail(allComments, commentFromAPost.getEmail()).size());
    }

    @Test
    public void commentEndpointCouldReturnACommentByItsId(){
        Post postWrittenByTestUser = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId())[0];;
        Comment commentFromAPost = PostResource.getPostComments(postWrittenByTestUser.getId())[0];

        Comment actualComment = CommentResource.getCommentById(commentFromAPost.getId());
        Assert.assertEquals(
                actualComment.getPostId(), postWrittenByTestUser.getId());
    }

    @Test
    public void validateCommentIdCouldNotBeDuplicated() {
        Comment[] allComments = CommentResource.getComments();
        Set<Integer> idsWithoutDuplicates = new HashSet<>(Filter.filterCommentIds(allComments));
        Assert.assertEquals(idsWithoutDuplicates.size(), allComments.length);
    }

    @Test
    public void validateCommentBodiesCouldNotBeEmpty() {
        Comment[] allComments = CommentResource.getComments();
        Assert.assertFalse("Comment body could be empty",
                Filter.areCommmentBodiesEmpty(allComments));
    }

    @Test
    public void validateCommentNamesCouldNotBeEmpty() {
        Comment[] allComments = CommentResource.getComments();
        Assert.assertFalse("Comment name could be empty",
                Filter.areCommentNamesEmpty(allComments));
    }
}
