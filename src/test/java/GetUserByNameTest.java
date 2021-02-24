import helper.Filter;
import jsonPlaceholder.UserResource;
import objectModels.Post;
import objectModels.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GetUserByNameTest {

    @Test
    public void validateUserWithExpectedNameExists() {
        UserResource userResource = new UserResource();
        User[] allExistingUsers = userResource.getUsers();
        boolean userExists = Filter.doesTheUserExist(allExistingUsers, "Delphine");
        Assert.assertTrue(String.format("User with name [%s] exists in users", "Delphine"), userExists);
    }

    @Test
    public void searchForAreUserByUsernameQuery() {
        UserResource userResource = new UserResource();
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        User[] actualUsers = userResource.getUsers(queryParameters);
        boolean userFound = Filter.doesTheUserExist(actualUsers,"Delphine");
        Assert.assertTrue(String.format("User with name [%s] exists in users", "Delphine"), userFound);
    }

    @Test
    public void userEndpointReturnsRequiredUserByItsId() {
        UserResource userResource = new UserResource();
        User[] allExistingUsers = userResource.getUsers();
        User expectedUser = Filter.filterUsersByName(allExistingUsers,"Delphine");
        User actualUser = userResource.getUserById(expectedUser.getId());
        Assert.assertTrue(expectedUser.equals(actualUser));
    }

    @Test
    public void userEndpointReturnsPostsPublishedForRequiredUser() {
        UserResource userResource = new UserResource();
        User[] allExistingUsers = userResource.getUsers();
        User expectedUser = Filter.filterUsersByName(allExistingUsers,"Delphine");
        Post[] posts = userResource.getUserPosts(expectedUser.getId());
        Assert.assertTrue(String.format("All posts returned by users endpoint are filtered by user: [%s]", expectedUser.getUsername()),
                Filter.arePostsFilteredByUserId(posts, expectedUser.getId()));
    }
}
