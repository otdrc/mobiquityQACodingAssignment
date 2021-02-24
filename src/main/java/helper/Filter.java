package helper;

import objectModels.Comment;
import objectModels.Post;
import objectModels.User;

import java.util.ArrayList;
import java.util.List;

public class Filter {

    public static User filterUsersByName(User[] users, String name) {
        User userFound = new User();
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                userFound = user;
                break;
            }
        }
      return userFound;
    }

    public static boolean doesTheUserExist(User[] users, String name) {
        boolean found = false;
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public static boolean doPostsFilteredByUserId(Post[] posts,int userId) {
        boolean postsAreFiltered = true;
        for (Post filteredPost : posts) {
            if (!filteredPost.getUserId().equals(userId)) {
                postsAreFiltered = false;
            }
        }
        return postsAreFiltered;
    }

    public static List<Comment> filterCommentsByEmail(Comment[] comments, String email){
        List<Comment> filteredComments = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getEmail().equals(email)) {
                filteredComments.add(comment);
            }
        }
        return filteredComments;
    }
}