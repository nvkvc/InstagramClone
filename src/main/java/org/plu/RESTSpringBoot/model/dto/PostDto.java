package org.plu.RESTSpringBoot.model.dto;

import java.io.Serializable;

public class PostDto implements Serializable {

    private int authorId;

    private String postName;

    public PostDto() {

    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
