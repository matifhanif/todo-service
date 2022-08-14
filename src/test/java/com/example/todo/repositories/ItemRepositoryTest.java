package com.example.todo.repositories;

import com.example.todo.models.Item;
import com.example.todo.models.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepo_test;


    @Test
    void findItemsByStatus_test() {
//        given
        List<Item> itemList = new ArrayList<>();
        itemList.add( new Item("Todo Item", Status.NOT_DONE, null, null,null));
        itemList.add( new Item("Todo Item", Status.NOT_DONE, null, null,null));
        itemList.add( new Item("Todo Item", Status.DONE, null, null,null));
        itemList.add( new Item("Todo Item", Status.PAST_DUE, null, null,null));
        itemRepo_test.saveAll(itemList);

//        when
        List<Item> item_notDone = itemRepo_test.findItemByStatus(Status.NOT_DONE);
        int expected = 2, noOfItem = item_notDone.size();

//        then
        assertThat(noOfItem).isEqualTo(expected);
    }
}