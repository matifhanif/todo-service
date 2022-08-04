package com.example.todo.controllers;

import com.example.todo.models.Item;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "api/v1/todo")
public class TodoController {


    @GetMapping(path ="items")
    public List<Item> getItems() {
        return List.of(new Item());
    }

    @GetMapping(path ="item/{id}")
    public Item getItem(@PathVariable(name = "id")  Long itemId) {
        return new Item();
    }

    @PostMapping(path = "item/{id}")
    public void AddItem(@PathVariable(name = "id")  Long itemId) {

    }

    @PutMapping (path = "item/{id}/desc")
    public void changeItemDesc(@PathVariable(name = "id") Long itemId,
                               @RequestParam(required = false) String desc) {

    }

    @PutMapping (path = "item/{id}/status")
    public void changeItemStatus(@PathVariable(name = "id") Long itemId,
                                 @RequestParam(name = "statusCode", required = true) @Min(1) @Max(3) int statusCode) {

    }
}
