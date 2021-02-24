import jsonPlaceholder.UserResource;
import objectModels.Post;
import objectModels.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class GetUserByNameTest {

    @Test
    public void validateUserWithExpectedNameExists() {
        UserResource userResource = new UserResource();
        User[] allExistingUsers = userResource.getUsers();
        boolean userExists = false;
        for (User user : allExistingUsers) {
            if (user.getUsername().equals("Delphine")) {
                userExists = true;
                break;
            }
        }
        Assert.assertTrue(String.format("User with name [%s] exists in users", "Delphine"), userExists);
    }

    @Test
    public void searchForAreUserByUsernameQuery() {
        UserResource userResource = new UserResource();
        HashMap<String, String> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User[] actualUsers = userResource.getUsers();
        boolean userFound = false;
        for (User user : actualUsers) {
            if (user.getUsername().equals("Delphine")) {
                userFound = true;
                break;
            }
        }
        Assert.assertTrue(String.format("User with name [%s] exists in users", "Delphine"), userFound);
    }

    @Test
    public void userEndpointReturnsRequiredUserByItsId() {
        UserResource userResource = new UserResource();
        User expectedUser = new User();
        User[] allExistingUsers = userResource.getUsers();
        for (User user : allExistingUsers) {
            if (user.getUsername().equals("Delphine")) {
                expectedUser = user;
                break;
            }
        }
        User actualUser = userResource.getUserById(expectedUser.getId());
        Assert.assertTrue(expectedUser.equals(actualUser));
    }

    @Test
    public void userEndpointReturnsPostsPublishedForRequiredUser() {
        UserResource userResource = new UserResource();
        User expectedUser = new User();
        User[] allExistingUsers = userResource.getUsers();
        for (User user : allExistingUsers) {
            if (user.getUsername().equals("Delphine")) {
                expectedUser = user;
                break;
            }
        }
        Post[] posts = userResource.getUserPosts(expectedUser.getId());
        boolean postsAreFiltered = true;
        for (Post filteredPost : posts) {
            if (!filteredPost.getUserId().equals(expectedUser.getId())) {
                postsAreFiltered = false;
            }
        }
        Assert.assertTrue(String.format("All posts returned by users endpoint are filtered for requested user: [%s]", expectedUser.getUsername()),
                postsAreFiltered);
    }
}
