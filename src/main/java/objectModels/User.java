package objectModels;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name, username, email, phone, website;
    private Address address;
    private Company company;
}
