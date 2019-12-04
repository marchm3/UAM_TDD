package com.capgemini.tdd.rest;

import com.capgemini.tdd.services.requestparams.NewGameRequestParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameRestServiceTest
{

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateNewGame() throws Exception
    {
        //given
        String jsonStr = createRequestParamsAsString();

        //when then
        mockMvc.perform(post("/game/new").content(jsonStr)
                                         .contentType(MediaType.APPLICATION_JSON)
                                         .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().is2xxSuccessful())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    private String createRequestParamsAsString()
    {
        NewGameRequestParams newGameRequestParams = new NewGameRequestParams("Adam", "Kasztan");
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = null;
        try {

            jsonStr = mapper.writeValueAsString(newGameRequestParams);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

}
