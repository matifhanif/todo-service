package com.example.todo.controllers;

import com.example.todo.models.Item;
import com.example.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(path ="items")
    public List<Item> getItems() {
        return todoService.getItems();
    }

    @GetMapping(path ="item/{id}")
    public Item getItem(@PathVariable(name = "id")  Long itemId) {
        return new Item();
    }

    @PostMapping(path = "item")
    public void AddItem(@RequestBody Item item) throws Exception {
        todoService.addItem(item);
    }

    @PutMapping (path = "item/{id}/desc")
    public void changeItemDesc(@PathVariable(name = "id") Long itemId,
                               @RequestParam(required = true) String desc) throws Exception {
        todoService.updateItemDesc(itemId, desc);
    }

    @PutMapping (path = "item/{id}/status")
    public void changeItemStatus(@PathVariable(name = "id") Long itemId,
                                 @RequestParam(name = "statusCode", required = true) @Min(0) @Max(1) int statusCode) throws Exception {
        todoService.updateItemStatus(itemId, statusCode);
    }
}
