
package app.controller;

import app.entity.User;
import app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @Test
    public void test_getAllClients_statusShouldBeOK() throws Exception {

        this.mockMvc.perform(get("/user/getClients"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void test_getAllEmployees_statusShouldBeOK() throws Exception {

        this.mockMvc.perform(get("/user/getEmployees"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void test_getUserById() throws Exception{
        User user = prepareUser();
        when(userService.findUser(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/getUser/{userId}", 1))
                .andExpect(status().isOk());
    }

    private User prepareUser() {
        User user = new User();
        user.setName("User name");
        user.setAmount_of_reservations(2);
        user.setEmail("sample@email.com");
        user.setEmployee(false);
        user.setSurname("surname");
        user.setUser_id(1);
        return user;
    }

}
