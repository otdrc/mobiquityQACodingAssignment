import jsonPlaceholder.JsonPlaceholderClientActions;
import objectModels.UserResource;
import org.junit.Assert;
import org.junit.Test;

public class GetUserByNameTest {

    @Test
    public void validateUserWithExpectedNameExists(){
        JsonPlaceholderClientActions action = new JsonPlaceholderClientActions();
        UserResource expectedUser = action.getUserByName("Delphine");
        Assert.assertEquals(expectedUser.getUsername(), "Delphine");
    }
}
