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

    public static boolean arePostsFilteredByUserId(Post[] posts, int userId) {
        boolean postsAreFiltered = true;
        for (Post filteredPost : posts) {
            if (!filteredPost.getUserId().equals(userId)) {
                postsAreFiltered = false;
            }
        }
        return postsAreFiltered;
    }

    public static List<Comment> filterCommentsByEmail(Comment[] comments, String email) {
        List<Comment> filteredComments = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getEmail().equals(email)) {
                filteredComments.add(comment);
            }
        }
        return filteredComments;
    }

    public static List<Integer> filterCommentIds(Comment[] comments) {
        List<Integer> filteredIds = new ArrayList<>();
        for (Comment comment : comments) {
            filteredIds.add(comment.getId());
        }
        return filteredIds;
    }

    public static List<Integer> filterCommentPostIds(Comment[] comments) {
        List<Integer> filteredPostIds = new ArrayList<>();
        for (Comment comment : comments) {
            filteredPostIds.add(comment.getPostId());
        }
        return filteredPostIds;
    }

    public static boolean areCommmentBodiesEmpty(Comment[] comments) {
        boolean isEmpty = false;
        for (Comment comment : comments) {
                isEmpty = comment.getBody().isEmpty();
        }
        return isEmpty;
    }

    public static boolean areCommentNamesEmpty(Comment[] comments) {
        boolean isEmpty = false;
        for (Comment comment : comments) {
            isEmpty = comment.getName().isEmpty();
        }
        return isEmpty;
    }
}