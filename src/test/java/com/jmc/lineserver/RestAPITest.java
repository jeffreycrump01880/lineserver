package com.jmc.lineserver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@WebAppConfiguration

public class RestAPITest {

	private MockMvc mockMvc;

	@Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LineserverController()).build();
    }

    @Test
    public void getAccount() throws Exception {
        this.mockMvc.perform(get("/lines/0").accept(MediaType.parseMediaType("application/json;charset=ISO-8859-1")))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
            .andExpect(content().string("aaa"));
           
    }
}
