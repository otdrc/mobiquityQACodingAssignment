package objectmodels;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer postId;
    private String name;
    private String email;
    private String body;

    public static class Builder {
        private Comment comment;

        public Builder() {
            comment = new Comment();
        }

        public Builder withId(Integer id) {
            comment.id = id;
            return this;
        }

        public Builder withPostId(Integer postId) {
            comment.postId = postId;
            return this;
        }

        public Builder withName(String name) {
            comment.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            comment.email = email;
            return this;
        }

        public Builder withBody(String body) {
            comment.body = body;
            return this;
        }

        public Comment build() {
            return comment;
        }
    }
}
