package helper;

import jsonPlaceholder.UserResource;
import objectModels.Post;
import objectModels.User;

import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static User getUserByUsername(String username) {
        UserResource userResource = new UserResource();
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", username);
        return userResource.getUsers(queryParameters)[0];
    }

    public static Post[] getAllPostsUserHas(String username) {
        UserResource userResource = new UserResource();
        Post[] posts = userResource.getUserPosts(getUserByUsername(username).getId());
        return posts;
    }
}
