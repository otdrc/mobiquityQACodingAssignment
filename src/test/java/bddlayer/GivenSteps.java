package bddlayer;

import io.cucumber.java.en.Given;
import jsonplaceholder.CommentResource;
import jsonplaceholder.PostResource;
import objectmodels.Comment;

public class GivenSteps {

    @Given("GET all existing comments")
    public void getAllComments() {
        SharedData.getInstance().setAllCommentsPublished(CommentResource.getComments());
    }

    @Given("I create a comment object with invalid email")
    public void iCreateACommentObjectWithInvalidEmail() {
        Comment newComment = new Comment.Builder()
                .withName("Comment with invalid email")
                .withBody("Some body here")
                .withEmail(".invalid@email.com")
                .withPostId(PostResource.getAllPosts().get(0).getId())
                .build();
        SharedData.getInstance().setNewComment(newComment);
    }
}
