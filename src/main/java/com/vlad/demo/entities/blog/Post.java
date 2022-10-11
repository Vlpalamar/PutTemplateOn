package com.vlad.demo.entities.blog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vlad.demo.entities.reg.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "posts")
public class Post {

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "post_categories",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name="category_id")}
    )
    @JsonIgnore
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    private String title; //+
    private String imgPath;
    private String recipe;
    private String tags;


    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;
}
