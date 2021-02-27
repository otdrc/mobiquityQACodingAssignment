package objectModels;

import lombok.Data;

@Data
public class Comment {
    private Integer id, postId;
    private String name, email, body;
}
