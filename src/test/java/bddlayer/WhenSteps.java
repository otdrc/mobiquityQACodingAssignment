package bddlayer;

import io.cucumber.java.en.When;
import jsonplaceholder.CommentResource;

public class WhenSteps {

    @When("send comment POST request")
    public void sendCommentPostRequest(){
        SharedData.getInstance().setResponseCode(CommentResource.postComment(SharedData.getInstance().getNewComment()));
    }
}
