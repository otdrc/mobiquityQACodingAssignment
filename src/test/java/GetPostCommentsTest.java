import helper.Helper;
import jsonPlaceholder.CommentResource;
import jsonPlaceholder.PostResource;
import objectModels.Comment;
import objectModels.Post;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetPostCommentsTest {

    @Test
    public void validateAllCommentsEmailsMatchFormat() {
        Pattern validEmailAddressRegex =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        boolean flag = true;
        CommentResource commentResource = new CommentResource();
        Comment[] comments = commentResource.getComments();
        List<Comment> allCommentsPublished = Arrays.asList(comments);
        for (Comment comment : allCommentsPublished) {
            Matcher matcher = validEmailAddressRegex.matcher(comment.getEmail());
            flag = matcher.find();
        }
        Assert.assertTrue("Not all Comments existing have valid email addresses ", flag);
    }

    @Test
    public void commentEndpointCouldBeQueriedToGetCommentFromAnEmail() {
        CommentResource commentResource = new CommentResource();
        PostResource postResource = new PostResource();
        Post postWrittenByTestUser = Helper.getAllPostsUserHas("Delphine")[0];
        Comment commentFromAPost = postResource.getPostCommets(postWrittenByTestUser.getId())[0];
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("email", commentFromAPost.getEmail());

        Comment[] allComments = commentResource.getComments();
        int commentFromExpectedEmail = 0;
        for (Comment comment : allComments) {
            if (comment.getEmail().equals(commentFromAPost.getEmail())) {
                commentFromExpectedEmail++;
            }
        }
        Comment[] actualComments = commentResource.getComments(queryParameters);
        Assert.assertTrue("Comment filter query is returning all comments with a particular email",
                actualComments.length == commentFromExpectedEmail);
    }

    @Test
    public void commentEndpointCouldReturnACommentByItsId(){
        CommentResource commentResource = new CommentResource();
        PostResource postResource = new PostResource();
        Post postWrittenByTestUser = Helper.getAllPostsUserHas("Delphine")[0];
        Comment commentFromAPost = postResource.getPostCommets(postWrittenByTestUser.getId())[0];

        Comment actualComment = commentResource.getCommentById(commentFromAPost.getId());
        Assert.assertTrue("Actual comment is returned by comment from a post as expected",
                actualComment.getPostId().equals(postWrittenByTestUser.getId()));
    }
}
