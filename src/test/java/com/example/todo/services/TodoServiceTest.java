package com.example.todo.services;

import com.example.todo.models.Item;
import com.example.todo.models.Status;
import com.example.todo.repositories.ItemRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock private ItemRepository itemRepository_mock;
    private TodoService todoService_test;

    @BeforeEach
    void setUp() {
        todoService_test = new TodoService(itemRepository_mock);
        List<Item> itemList = new ArrayList<>();
        itemList.add( new Item("Todo Item", Status.NOT_DONE, null, null,null));
        itemList.add( new Item("Todo Item", Status.NOT_DONE, null, null,null));
        itemRepository_mock.saveAll(itemList);
    }


    @Test
    void getItems() {
        //        when
        todoService_test.getItems();

        //then
        verify(itemRepository_mock).findAll();
    }

    @Test
    @Disabled
    void getNotDoneItems() {
    }

    @Test
    @Disabled
    void getItem_test() {
    }

    @Test
    void addItem() throws Exception {
        // given
        Item item = new Item("Todo item desc", Status.NOT_DONE, null, null, null);

        // when
        todoService_test.addItem(item);

        // then
        ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);
        verify(itemRepository_mock).save(itemArgumentCaptor.capture());
        Item capturedItem = itemArgumentCaptor.getValue();
        assertThat(capturedItem).isEqualTo(item);
    }


    @Test
    void addItemWithInvalidParam() throws Exception {
        // given
        Item item = new Item("", Status.NOT_DONE, null, null, null);
//
        // when
        // then
        assertThatThrownBy(() -> todoService_test.addItem(item))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Invalid Request Parameters");

    }

    @Test
    void updateItemDesc() throws Exception {
        // given
        Item item = new Item("Todo item desc", Status.NOT_DONE, null, null, null);
        item.setId(1l);

        when(itemRepository_mock.findById(Mockito.any(Long.class))).thenReturn(Optional.of(item));
        given(itemRepository_mock.existsById(item.getId())).willReturn(true);

        // when
        String nDesc = "New Desc";
        todoService_test.updateItemDesc(item.getId(), nDesc);

        // then
        assertThat(item.getDesc()).isEqualTo(nDesc);
    }

    @Test
    @Disabled
    void updateItemStatus() {
    }

    @Test
    @Disabled
    void isDueDateExpired() {

    }
}