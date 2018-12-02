package app.entity;

import java.time.LocalDate;
import java.util.List;

public class ReservationDTO {
    private Integer id;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean payed;

    private Integer userId;

    private List<Integer> rooms;

    private List<Double> averageCosts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getRooms() {
        return rooms;
    }

    public void setRooms(List<Integer> rooms) {
        this.rooms = rooms;
    }

    public List<Double> getAverageCosts() {
        return averageCosts;
    }

    public void setAverageCosts(List<Double> averageCosts) {
        this.averageCosts = averageCosts;
    }
}
