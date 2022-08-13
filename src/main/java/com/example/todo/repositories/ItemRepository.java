package com.example.todo.repositories;

import com.example.todo.models.Item;
import com.example.todo.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query("SELECT i FROM Item i WHERE i.status = ?1 ")
    List<Item> findItemByStatus(Status status);


}
