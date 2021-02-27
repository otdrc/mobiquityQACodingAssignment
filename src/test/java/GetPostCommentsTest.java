import helper.Filter;
import jsonplaceholder.CommentResource;
import jsonplaceholder.PostResource;
import jsonplaceholder.UserResource;
import objectmodels.Comment;
import objectmodels.Post;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.*;

public class GetPostCommentsTest {

    @Test
    public void validateAllCommentsEmailsMatchFormat() {
        List<Comment> allCommentsPublished = CommentResource.getComments();
        Assertions
                .assertThat(Filter.areCommentEmailsMatchingRegex(allCommentsPublished))
                .as("All Comment email addresses are in valid email format")
                .isTrue();
    }

    @Test
    public void commentEndpointCouldBeQueriedToGetCommentFromAnEmail() {
        Post postWrittenByTestUser = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId()).get(0);
        Comment commentFromAPost = PostResource.getPostComments(postWrittenByTestUser.getId()).get(0);
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("email", commentFromAPost.getEmail());
        List<Comment> commentsFilteredByEmail = Filter.filterCommentsByEmail(CommentResource.getComments(), commentFromAPost.getEmail());
        Assertions
                .assertThat(CommentResource.getComments(queryParameters))
                .hasSameSizeAs(commentsFilteredByEmail);
    }

    @Test
    public void commentEndpointCouldReturnACommentByItsId() {
        Post postWrittenByTestUser = UserResource
                .getUserPosts(Filter.filterUsersByName(UserResource.getUsers(), "Delphine").getId()).get(0);
        Comment commentFromAPost = PostResource.getPostComments(postWrittenByTestUser.getId()).get(0);
        Comment actualComment = CommentResource.getCommentById(commentFromAPost.getId());
        Assertions
                .assertThat(actualComment.getPostId())
                .isSameAs(postWrittenByTestUser.getId());
    }

    @Test
    public void validateCommentIdCouldNotBeDuplicated() {
        List<Comment> allComments = CommentResource.getComments();
        Set<Integer> idsWithoutDuplicates = new HashSet<>(Filter.filterCommentIds(CommentResource.getComments()));
        Assertions
                .assertThat(idsWithoutDuplicates)
                .hasSameSizeAs(allComments);
    }

    @Test
    public void validateCommentBodiesCouldNotBeEmpty() {
        Assertions
                .assertThat(Filter.areCommmentBodiesEmpty(CommentResource.getComments()))
                .as("Comment body could not be empty")
                .isFalse();
    }

    @Test
    public void commentsEndpointCouldNotReturnAnyDataForInvalidQueryParameter() {
        Integer postIdOutOfBound = PostResource.getAllPosts().size() + 1;
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("postId", postIdOutOfBound);
        List<Comment> commentsFound = CommentResource.getComments(queryParameters);
        Assertions
                .assertThat(commentsFound)
                .as("Non existing post with id [%d] could not have any comments", postIdOutOfBound)
                .isEmpty();
    }
}
