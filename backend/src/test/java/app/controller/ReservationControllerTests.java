
package app.controller;

import app.entity.Reservation;
import app.entity.ReservationDTO;
import app.entity.User;
import app.repository.UserRepository;
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

import static org.junit.Assert.*;
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

    @Autowired
    ReservationService reservationService2;

    @Autowired
    UserRepository userRepository;

    @Test
    public void test_getAllReservations_statusShouldBeOK() throws Exception {

        this.mockMvc.perform(get("/reservation/getAll"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void test_geReservationsForUser_statusShouldBeOK() throws Exception {
        Reservation reservation = prepareReservation();
        List reservationList = new ArrayList();
        reservationList.add(reservation);
        when(reservationService.getReservationForUser(1)).thenReturn(reservationList);
        this.mockMvc.perform(get("/reservation//get/{userId}", 1))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = "classpath:createUser.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:deleteReservation.sql", executionPhase = AFTER_TEST_METHOD)
    public void test_AddReservation_statusShouldBeOK()  {
        ReservationDTO reservation = prepareReservationDTO();
        Reservation savedReservation = reservationService2.addNewReservation(reservation);
        assertTrue(savedReservation.getReservationIdList().isEmpty());
        assertEquals(savedReservation.getEndDate(), reservation.getEndDate());
        assertEquals(savedReservation.getStartDate(), reservation.getStartDate());
        assertEquals(savedReservation.getUser().getUser_id(), reservation.getUserId());
        assertFalse(savedReservation.isPayed());


    }

    private ReservationDTO prepareReservationDTO() {
        ReservationDTO reservationDTO = new ReservationDTO();
        List<Integer> rooms = new ArrayList<>();
        List<Double> averageCost = new ArrayList<>();
        averageCost.add((double) 50);
        reservationDTO.setId(1000);
        reservationDTO.setPayed(false);
        reservationDTO.setStartDate(LocalDate.now());
        reservationDTO.setEndDate(LocalDate.now());
        reservationDTO.setUserId(1000);
        reservationDTO.setRooms(rooms);
        reservationDTO.setAverageCosts(averageCost);
        return reservationDTO;
    }


    @Test
    @Sql(scripts = "classpath:deleteReservation.sql", executionPhase = AFTER_TEST_METHOD)
    @Sql(scripts = "classpath:createReservation.sql", executionPhase = BEFORE_TEST_METHOD)
    public void test_DeleteUser_statusShouldBeOK() throws Exception {
        this.mockMvc.perform(delete("/reservation/delete/{id}", 1000))
                .andDo(print()).andExpect(status().isOk());
    }

    private Reservation prepareReservation() {
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