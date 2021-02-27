import helper.Filter;
import jsonPlaceholder.UserResource;
import objectModels.Post;
import objectModels.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetUserByNameTest {

    @Test
    public void validateUserWithExpectedNameExists() {
        List<User> allExistingUsers = UserResource.getUsers();
        boolean userExists = Filter.doesTheUserExist(allExistingUsers, "Delphine");
        Assert.assertTrue(String.format("User with name [%s] exists in users", "Delphine"), userExists);
    }

    @Test
    public void searchForAreUserByUsernameQuery() {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        List<User> actualUsers = UserResource.getUsers(queryParameters);
        boolean userFound = Filter.doesTheUserExist(actualUsers,"Delphine");
        Assert.assertTrue(String.format("User with name [%s] exists in users", "Delphine"), userFound);
    }

    @Test
    public void userEndpointReturnsRequiredUserByItsId() {
        List<User> allExistingUsers = UserResource.getUsers();
        User expectedUser = Filter.filterUsersByName(allExistingUsers,"Delphine");
        User actualUser = UserResource.getUserById(expectedUser.getId());
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void userEndpointReturnsPostsPublishedForRequiredUser() {
        List<User> allExistingUsers = UserResource.getUsers();
        User expectedUser = Filter.filterUsersByName(allExistingUsers,"Delphine");
        List<Post> posts = UserResource.getUserPosts(expectedUser.getId());
        Assert.assertTrue(String.format("All posts returned by users endpoint are filtered by user: [%s]", expectedUser.getUsername()),
                Filter.arePostsFilteredByUserId(posts, expectedUser.getId()));
    }
}
