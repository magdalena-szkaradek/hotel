
package app.controller;

import app.entity.Reservation;
import app.entity.User;
import app.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
public class ReservationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    ReservationService reservationService;

    @Test
    public void test_getAllReservations_statusShouldBeOK() throws Exception {

        this.mockMvc.perform(get("/reservation/getAll"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void test_geReservationsForUser_statusShouldBeOK() throws Exception {
        Reservation reservation = prepareReservetion();
        List reservationList = new ArrayList();
        reservationList.add(reservation);
        when(reservationService.getReservationForUser(1)).thenReturn(reservationList);
        this.mockMvc.perform(get("/reservation//get/{userId}", 1))
                .andDo(print()).andExpect(status().isOk());
    }


    @Test
    @Sql(scripts = "classpath:deleteReservation.sql", executionPhase = AFTER_TEST_METHOD)
    @Sql(scripts = "classpath:createReservation.sql", executionPhase = BEFORE_TEST_METHOD)
    public void test_DeleteUser_statusShouldBeOK() throws Exception {
        this.mockMvc.perform(delete("/reservation/delete/{id}", 1000))
                .andDo(print()).andExpect(status().isOk());
    }

    private Reservation prepareReservetion() {
        Reservation reservation = new Reservation();
        reservation.setPayed(false);
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now());
        User user = new User();
        user.setUser_id(1);
        reservation.setUser(user);
        return reservation;
    }

}