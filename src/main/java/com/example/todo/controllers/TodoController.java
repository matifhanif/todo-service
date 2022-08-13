package com.example.todo.controllers;

import com.example.todo.models.Item;
import com.example.todo.services.TodoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(path = "api/v1/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(path = {"items","items/{status}"})
    public List<Item> getItems(@PathVariable(name = "status",required = false) Optional<String> status) {
        if (status.isPresent() && StringUtils.equals(status.get(), "notdone")) {
            return todoService.getNotDoneItems();
        }
        return todoService.getItems();
    }

    @GetMapping(path ="item/{id}")
    public Optional<Item> getItem(@PathVariable(name = "id")  Long itemId) throws Exception {
        return todoService.getItem(itemId);
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
