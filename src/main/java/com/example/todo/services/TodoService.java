package com.example.todo.services;

import com.example.todo.models.Item;
import com.example.todo.models.Status;
import com.example.todo.repositories.ItemRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final ItemRepository itemRepository;
    private final Clock clock;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public TodoService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        clock = Clock.systemUTC();
    }

    @Transactional
    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();
        items.stream()
                .filter(item -> isDueDateExpired(item) )
                .forEach(item -> item.setStatus(Status.PAST_DUE));
        return items;
    }

    @Transactional
    public List<Item> getNotDoneItems() {
        List<Item> items = itemRepository.findItemByStatus(Status.NOT_DONE);
        items.stream()
                .filter(item -> isDueDateExpired(item) )
                .forEach(item -> item.setStatus(Status.PAST_DUE));
        List<Item> ndItems = items.stream().filter(item -> item.getStatus() == Status.NOT_DONE).collect(Collectors.toList());
        return ndItems;
    }

    @Transactional
    public Optional<Item> getItem(Long itemId) throws Exception {
        Optional<Item> item = itemRepository.findById(itemId);
        if ( itemRepository.existsById(itemId) && isDueDateExpired(item.get())) {
            item.get().setStatus(Status.PAST_DUE);
        }
        return item;
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

    
    public boolean isDueDateExpired(Item item) {
        LocalDateTime now = LocalDateTime.parse(LocalDateTime.now(clock).format(formatter));
        if (item.getDtDue() != null
                && now.isAfter(item.getDtDue())
                && item.getStatus() == Status.NOT_DONE) {
            return true;
        }
        return false;
    }
}
