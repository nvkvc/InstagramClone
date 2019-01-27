package org.plu.RESTSpringBoot.model.entities;

import java.io.Serializable;

public class ShareLikeDto implements Serializable {

    private long userId;
    private long postId;

    public ShareLikeDto() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
