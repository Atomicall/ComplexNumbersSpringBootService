package com.ru.org.name;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private Controller controller;



    @Test
    public void testCalc() throws Exception
    {
        this.mockMvc.perform (get("http://localhost:8080/calculate/?RealPart=1&ImgPart=3") ).andExpect(status().isOk())
                .andExpect(content().json("{mod:3.1622776601683795,ph:1.2490457723982544}"));



    }

}