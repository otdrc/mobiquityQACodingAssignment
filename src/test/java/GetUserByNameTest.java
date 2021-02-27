import helper.Filter;
import jsonplaceholder.UserResource;
import objectmodels.Post;
import objectmodels.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetUserByNameTest {

    @Test
    public void validateUserWithExpectedNameExists() {
        List<User> allExistingUsers = UserResource.getUsers();
        boolean userExists = Filter.doesTheUserExist(allExistingUsers, "Delphine");
        Assertions
                .assertThat(userExists)
                .as("User with name [%s] exists in users", "Delphine", userExists)
                .isTrue();
    }

    @Test
    public void searchForAreUserByUsernameQuery() {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("username", "Delphine");
        List<User> actualUsers = UserResource.getUsers(queryParameters);
        boolean userFound = Filter.doesTheUserExist(actualUsers,"Delphine");
        Assertions
                .assertThat(userFound)
                .as("User with name [%s] could be found in users", "Delphine", userFound)
                .isTrue();
    }

    @Test
    public void userEndpointReturnsRequiredUserByItsId() {
        List<User> allExistingUsers = UserResource.getUsers();
        User expectedUser = Filter.filterUsersByName(allExistingUsers,"Delphine");
        User actualUser = UserResource.getUserById(expectedUser.getId());
        Assertions
                .assertThat(actualUser)
                .isEqualTo(expectedUser);
    }

    @Test
    public void userEndpointReturnsPostsPublishedForRequiredUser() {
        List<User> allExistingUsers = UserResource.getUsers();
        User expectedUser = Filter.filterUsersByName(allExistingUsers,"Delphine");
        List<Post> posts = UserResource.getUserPosts(expectedUser.getId());
        Assertions
                .assertThat(Filter.arePostsFilteredByUserId(posts, expectedUser.getId()))
                .as("All posts returned by users endpoint are filtered by username: [%s]", expectedUser.getUsername())
                .isTrue();
    }
}
