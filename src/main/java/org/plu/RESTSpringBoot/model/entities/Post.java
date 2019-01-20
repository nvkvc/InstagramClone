package org.plu.RESTSpringBoot.model.entities;

import org.plu.RESTSpringBoot.model.entities.ApplicationUser;

import javax.persistence.*;
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

    private String contentPath;

    public long getId() {
        return id;
    }

    public ApplicationUser getAuthor() {
        return author;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthor(ApplicationUser author) {
        this.author = author;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }
}
