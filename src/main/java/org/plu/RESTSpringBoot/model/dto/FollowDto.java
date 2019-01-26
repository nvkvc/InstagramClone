package org.plu.RESTSpringBoot.model.dto;

public class FollowDto {
    private String followerUsername;
    private String followingUsername;

    public FollowDto() {
    }

    public String getFollowerUsername() {
        return followerUsername;
    }

    public void setFollowerUsername(String followerUsername) {
        this.followerUsername = followerUsername;
    }

    public String getFollowingUsername() {
        return followingUsername;
    }

    public void setFollowingUsername(String followingUsername) {
        this.followingUsername = followingUsername;
    }
}
