package org.plu.RESTSpringBoot.model.entities;

import org.plu.RESTSpringBoot.model.entities.ApplicationUser;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private ApplicationUser author;

    @ManyToMany(mappedBy = "sharedPosts")
    private List<ApplicationUser> sharedByUsers;

    @ManyToMany(mappedBy = "likedPosts")
    private List<ApplicationUser> likedByUsers;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    private String originalFileName;

    public Post() {
        sharedByUsers = new ArrayList<>();
        likedByUsers = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public long getId() {
        return id;
    }

    public ApplicationUser getAuthor() {
        return author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthor(ApplicationUser author) {
        this.author = author;
    }

    public List<ApplicationUser> getSharedByUsers() {
        return sharedByUsers;
    }

    public void setSharedByUsers(List<ApplicationUser> sharedByUsers) {
        this.sharedByUsers = sharedByUsers;
    }

    public List<ApplicationUser> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(List<ApplicationUser> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
