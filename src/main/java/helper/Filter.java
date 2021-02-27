package helper;

import objectModels.Comment;
import objectModels.Post;
import objectModels.User;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Filter {

    public static User filterUsersByName(List<User> users, String name) {
        return users
                .stream()
                .filter(user -> user.getUsername().equals(name))
                .collect(Collectors.toList()).get(0);
    }

    public static boolean doesTheUserExist(List<User> users, String name) {
        return users
                .stream()
                .anyMatch(user -> user.getUsername().equals(name));
    }

    public static boolean arePostsFilteredByUserId(List<Post> posts, int userId) {
        return posts
                .stream()
                .allMatch(p -> p.getUserId().equals(userId));
    }

    public static List<Comment> filterCommentsByEmail(List<Comment> comments, String email) {
        return comments
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .collect(Collectors.toList());
    }

    public static List<Integer> filterCommentIds(List<Comment> comments) {
        return comments
                .stream()
                .map(Comment::getId)
                .collect(Collectors.toList());
    }

    public static List<Integer> filterCommentPostIds(List<Comment> comments) {
        return comments
                .stream()
                .map(Comment::getPostId)
                .collect(Collectors.toList());
    }

    public static boolean areCommmentBodiesEmpty(List<Comment> comments) {
        return comments
                .stream()
                .anyMatch(comment -> comment.getBody().isEmpty());
    }

    public static boolean areCommentNamesEmpty(List<Comment> comments) {
        return comments
                .stream()
                .anyMatch(comment -> comment.getName().isEmpty());
    }

    public static boolean areCommentEmailsMatchingRegex(List<Comment> comments) {
        Pattern validEmailAddressRegex =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return comments
                .stream()
                .map(Comment::getEmail)
                .allMatch(validEmailAddressRegex.asPredicate());
    }
}