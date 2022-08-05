package com.example.todo.services;

import com.example.todo.models.Item;
import com.example.todo.models.Status;
import com.example.todo.repositories.ItemRepository;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final ItemRepository itemRepository;

    public TodoService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItemDesc(Long itemId, String desc) throws Exception {

        Optional<Item> item = itemRepository.findById(itemId) ;
        if ( !itemRepository.existsById(itemId) || StringUtils.isBlank(desc) ) {
            throw new Exception("Invalid Request Parameters");
        }
        item.get().setDesc(desc);

    }

    @Transactional
    public void updateItemStatus(Long itemId, int status) throws Exception {
        Optional<Item> item = itemRepository.findById(itemId) ;
        if ( !itemRepository.existsById(itemId) ) {
            throw new Exception("Invalid Parameters");
        }
        item.get().setStatus(Status.DONE);
    }
}
