package br.com.grupoacert.conversion.api.controller;

import br.com.grupoacert.conversion.api.controller.ConversionController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ConversionControllerTests {

    @InjectMocks
    private ConversionController controller;

    @Autowired
    private MockMvc mockMvc;

    @Before()
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void toCelsius() throws Exception {
        String json = "{\n" +
                "  \"temperature\": 60.0 \n" +
                "}";

        mockMvc.perform(post("/conversion/toCelsius")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature", Matchers.is(15.56)));
    }

    @Test
    void toFahrenheit() throws Exception{
        String json = "{\n" +
                "  \"temperature\": 15.56 \n" +
                "}";

        mockMvc.perform(post("/conversion/toFahrenheit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature", Matchers.is(60.01)));
    }


    @Test
    void getAll() {
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/conversion/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(4)));
    }
}