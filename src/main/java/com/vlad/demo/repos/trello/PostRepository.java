package com.vlad.demo.repos.trello;

import com.vlad.demo.entities.trello.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
