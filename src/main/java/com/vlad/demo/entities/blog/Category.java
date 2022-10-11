package com.vlad.demo.entities.blog;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "categories")
public class Category {

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
//    @JoinTable(
//            name = "post_categories",
//            joinColumns = {@JoinColumn(name = "category_id")},
//            inverseJoinColumns = {@JoinColumn(name="post_id")}
//    )
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;











    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
