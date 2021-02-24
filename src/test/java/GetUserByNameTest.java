import jsonPlaceholder.UserResource;
import objectModels.User;
import org.junit.Assert;
import org.junit.Test;

public class GetUserByNameTest {

    @Test
    public void validateUserWithExpectedNameExists(){
        UserResource userResource = new UserResource();
        User expectedUser = userResource.getUserByName("Delphine");
        Assert.assertEquals(expectedUser.getUsername(), "Delphine");
    }
}
