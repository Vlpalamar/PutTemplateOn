package com.vlad.demo.repos.trello;

import com.vlad.demo.entities.trello.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository  extends CrudRepository<Card, Long> {
}
