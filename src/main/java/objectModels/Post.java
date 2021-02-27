package objectModels;

import lombok.Data;

@Data
public class Post {
    private Integer id, userId;
    private String title, body;
}
