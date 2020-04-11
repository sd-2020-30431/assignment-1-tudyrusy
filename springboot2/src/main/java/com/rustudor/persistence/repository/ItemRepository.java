package com.rustudor.persistence.repository;

import com.rustudor.entity.Item;
import com.rustudor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    Item findByNameAndUserFK(String name, User userFK);
}
