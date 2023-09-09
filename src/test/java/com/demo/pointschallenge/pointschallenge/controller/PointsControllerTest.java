package com.demo.pointschallenge.pointschallenge.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.pointschallenge.pointschallenge.dvo.Transactions;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PointsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCalculateRewards() throws Exception {

        List<Transactions> transactionList=Arrays.asList(
            new Transactions("Adam", 120, "08/2023"),
            new Transactions("Rakesh", 120, "08/2023"),
            new Transactions("Adam", 90, "07/2023"),
            new Transactions("Adam", 120, "07/2023")
        );

          mockMvc.perform(MockMvcRequestBuilders
                .post("/api/points")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(transactionList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.Adam.08/2023").value(90))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Adam.07/2023").value(130));

              
    }

    private String asJson(List<Transactions> transactionList) {
        try{
        return new ObjectMapper().writeValueAsString(transactionList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
