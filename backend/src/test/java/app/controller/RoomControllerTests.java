
package app.controller;

import app.entity.Room;
import app.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RoomControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    RoomService roomService;

    @Test
    public void test_getAllRooms_statusShouldBeOK() throws Exception {

        this.mockMvc.perform(get("/room/getAll"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void test_getRoomById() throws Exception{
        Room room = prepareRoom();
        when(roomService.findRoomById(1)).thenReturn(Optional.of(room));

        mockMvc.perform(get("/room/getRoomById/{id}", 1))
                .andExpect(status().isOk());
    }

    private Room prepareRoom() {
        Room room = new Room();
        room.setBeds(2);
        room.setId(1);
        room.setName("Name");
        room.setPrice((double) 30);
        return room;
    }


    @Test
    @Sql(scripts = "classpath:deleteRoom.sql", executionPhase = AFTER_TEST_METHOD)
    @Sql(scripts = "classpath:createRoom.sql", executionPhase = BEFORE_TEST_METHOD)
    public void test_DeleteRoom_statusShouldBeOK() throws Exception {
        this.mockMvc.perform(delete("/room/delete/{id}", 1000))
                .andDo(print()).andExpect(status().isOk());
    }
}