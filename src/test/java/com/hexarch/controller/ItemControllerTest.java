package com.hexarch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexarch.domain.Item;
import com.hexarch.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.hexarch.constants.ItemConstants.ITEM_URL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

    MockMvc mockMvc;

    @Mock
    ItemService itemService;

    @InjectMocks
    private ItemController itemController;


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    public void saveItem() throws Exception {
        Item item = new Item(100,"SKU001", "Iphone XR", 999.99);
         String itemBody = new ObjectMapper().writeValueAsString(item);
        when(itemService.saveItem(any())).thenReturn(item);

        mockMvc.perform(post((ITEM_URL)).content(itemBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }
}
