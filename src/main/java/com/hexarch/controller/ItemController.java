package com.hexarch.controller;

import com.hexarch.domain.Item;
import com.hexarch.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.hexarch.constants.ItemConstants.SINGLE_ITEM;
import static com.hexarch.constants.ItemConstants.ITEM_URL;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping(ITEM_URL)
    public ResponseEntity<Item> saveItem(@RequestBody @Valid Item item){
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemService.saveItem(item));
    }


    @GetMapping(SINGLE_ITEM)
    public ResponseEntity<Item> retrieveItem(@PathVariable("id") Integer itemId){
        return  ResponseEntity.ok(itemService.retrieveItem(itemId));
    }

    @PutMapping(ITEM_URL)
    public ResponseEntity<Item> updateItem(@RequestBody Item item){
        return  ResponseEntity.ok(itemService.updateItem(item));
    }

    @DeleteMapping(SINGLE_ITEM)
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Integer itemId){
        itemService.deleteItem(itemId);
        return  ResponseEntity.ok().build();
    }
}
