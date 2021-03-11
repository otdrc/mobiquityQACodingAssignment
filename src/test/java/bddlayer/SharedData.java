package bddlayer;

import objectmodels.Comment;

import java.util.List;

public class SharedData {
    private static SharedData instance;

    private SharedData() {
    }

    private List<Comment> allCommentsPublished;
    private Comment newComment;
    private Integer responseCode;

    public static SharedData getInstance() {
        if (instance==null) {
            instance = new SharedData();
        }
        return instance;
    }

    public List<Comment> getAllCommentsPublished() {
        return this.instance.allCommentsPublished;
    }

    public void setAllCommentsPublished(List<Comment> allCommentsPublished) {
        this.instance.allCommentsPublished = allCommentsPublished;
    }

    public Comment getNewComment() {
        return this.instance.newComment;
    }

    public void setNewComment(Comment newComment) {
        this.instance.newComment = newComment;
    }

    public Integer getResponseCode() {
        return this.instance.responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.instance.responseCode = responseCode;
    }
}
