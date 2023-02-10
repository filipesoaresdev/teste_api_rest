package com.example.fiducial_project.services;


import com.example.fiducial_project.controller.NomeController;
import com.example.fiducial_project.model.Nome;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(NomeController.class)
public class NomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NomeService nomeService;

    @Test
    public void getTodosNomes() throws Exception
    {
        Nome nome = new Nome();
        nome.setId(1);
        nome.setNome("Nome");
        Mockito.when(nomeService.getTodosNomes()).thenReturn(List.of(nome));
        MvcResult mvcResult= mvc.perform(MockMvcRequestBuilders
                        .get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").exists()).andReturn();
        Assertions.assertEquals("[{\"id\":1,\"nome\":\"Nome\"}]",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void existeNome() throws Exception
    {
        Mockito.when(nomeService.existeNome("nome")).thenReturn(true);
        MvcResult mvcResult= mvc.perform(MockMvcRequestBuilders
                        .get("/userExists?nome=nome")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assertions.assertEquals("true",mvcResult.getResponse().getContentAsString());
    }


}
