package org.plu.RESTSpringBoot.model.dto;

import java.io.Serializable;

public class CommentDto implements Serializable {

    private String comment;
    private String username;
    private long postId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
