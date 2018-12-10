
package app.controller;

import app.entity.SeasonPrice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SeasonPriceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_getAllSeasonPrices_statusShouldBeOK() throws Exception {

        this.mockMvc.perform(get("/seasonPrice/getAll"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = "classpath:deleteSeasonPrice.sql", executionPhase = AFTER_TEST_METHOD)
    @Sql(scripts = "classpath:createSeasonPrice.sql", executionPhase = BEFORE_TEST_METHOD)
    public void test_DeleteSeasonPrices_statusShouldBeOK() throws Exception {
        this.mockMvc.perform(delete("/seasonPrice/delete/{id}", 1000))
                .andDo(print()).andExpect(status().isOk());
    }
}
