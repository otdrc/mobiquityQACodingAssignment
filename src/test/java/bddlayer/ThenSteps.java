package bddlayer;

import helper.Filter;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;

public class ThenSteps {

    @Then("validate comments emails are in proper format")
    public void validateEmailsMatchTheFormat() {
        Assertions
                .assertThat(Filter.areCommentEmailsMatchingRegex(SharedData.getInstance().getAllCommentsPublished()))
                .as("All Comment email addresses are in valid email format")
                .isTrue();
    }

    @Then("validate I receive 4XX Client Error response code")
    public void validatePostRequestReturnsTheUserError() {
        Assertions
                .assertThat(SharedData.getInstance().getResponseCode())
                .as("Comment with email [%s] could not be created", SharedData.getInstance().getNewComment().getEmail())
                .isBetween(400, 499);
    }
}
