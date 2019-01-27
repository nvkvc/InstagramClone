package org.plu.RESTSpringBoot.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    private String username;

    private String password;

    private String email;

    @OneToMany(mappedBy = "author")
    private List<Post> userPosts;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "posts_shared_by_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> sharedPosts;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "posts_liked_by_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> likedPosts;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "follower_following", joinColumns = {
            @JoinColumn(name = "follower_id", referencedColumnName = "user_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "following_id", referencedColumnName = "user_id", nullable = false)})
    private List<ApplicationUser> following;

    @ManyToMany(mappedBy = "following")
    private List<ApplicationUser> followedBy;

    public ApplicationUser() {
        userPosts = new ArrayList<>();
        sharedPosts = new ArrayList<>();
        following = new ArrayList<>();
        followedBy = new ArrayList<>();
        likedPosts = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public List<Post> getSharedPosts() {
        return sharedPosts;
    }

    public void setSharedPosts(List<Post> sharedPosts) {
        this.sharedPosts = sharedPosts;
    }

    public List<ApplicationUser> getUsersFollowing() {
        return following;
    }

    public void setUsersFollowing(List<ApplicationUser> usersFollowing) {
        this.following = usersFollowing;
    }

    public List<ApplicationUser> getFollowing() {
        return following;
    }

    public void setFollowing(List<ApplicationUser> following) {
        this.following = following;
    }

    public List<ApplicationUser> getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(List<ApplicationUser> followedBy) {
        this.followedBy = followedBy;
    }

    public List<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }
}