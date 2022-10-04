package com.vlad.demo.entities.trello;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cards")
public class Card {

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    private String title;
    private String text;

    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;
}
