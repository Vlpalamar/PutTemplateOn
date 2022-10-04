package com.vlad.demo.entities.trello;

import com.vlad.demo.entities.reg.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "posts")
public class Post {

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

    private String title;
    private String imgPath;
    private String recipe;
    private String tegs;


    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;
}
