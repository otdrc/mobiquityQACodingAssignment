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
        Comment newComment = new Comment();
        newComment.setName("Comment with invalid email");
        newComment.setBody("Some body here");
        newComment.setPostId(PostResource.getAllPosts().get(0).getId());
        newComment.setEmail(".invalid@email.com");
        SharedData.getInstance().setNewComment(newComment);
    }
}
