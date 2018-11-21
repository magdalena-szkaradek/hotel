package app.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date startDate;

    private Date endDate;

    private boolean payed;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;



    @OneToMany
    @JoinColumn(name="reservation_id", nullable = false)
    private List<ReservationId> reservationIdList;

    public Integer getReservation_id() {
        return id;
    }

    public void setReservation_id(Integer reservation_id) {
        this.id = reservation_id;
    }

    public List<ReservationId> getReservationIdList() {
        return reservationIdList;
    }

    public void setReservationIdList(List<ReservationId> reservationIdList) {
        this.reservationIdList = reservationIdList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return payed == that.payed &&
                Objects.equals(id, that.id) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, payed, user);
    }
}