package jsonPlaceholder;

import objectModels.User;

import static io.restassured.RestAssured.given;

public class UserResource extends JsonPlaceholder {
    public UserResource() {
        super();
    }

    public User getUserByName(String username) {
        User[] allUsers = given()
                .spec(requestSpecification)
                .get(Endpoint.USERS)
                .body()
                .as(User[].class);

        User user = new User();
        for (User currentUser : allUsers) {
            if (currentUser.getUsername().equals(username)) {
                user = currentUser;
                break;
            }
        }
        return user;
    }
}
