package com.example.todo.services;

import com.example.todo.models.Item;
import com.example.todo.models.Status;
import com.example.todo.repositories.ItemRepository;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final ItemRepository itemRepository;
    private final Clock clock;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public TodoService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        clock = Clock.systemUTC();
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public void addItem(Item item) throws Exception {
        if (StringUtils.isBlank(item.getDesc()) )
            throw new Exception("Invalid Request Parameters");
        item.setDtCreated(LocalDateTime.parse(LocalDateTime.now(clock).format(formatter)));
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
    public void updateItemStatus(Long itemId, int statusCode) throws Exception {
        Optional<Item> item = itemRepository.findById(itemId) ;
        if ( !itemRepository.existsById(itemId) ) {
            throw new Exception("Invalid Parameters");
        }
        item.get().setStatus(Status.valueOf(statusCode));
        if (Status.valueOf(statusCode) == Status.DONE) {
            item.get().setDtCompleted(LocalDateTime.parse(LocalDateTime.now(clock).format(formatter)));
        }
    }
}
