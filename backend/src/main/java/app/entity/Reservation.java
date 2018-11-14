package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer reservation_id;


    private Date startDate;

    private Date endDate;

    @Column(columnDefinition = "tinyint", nullable = false)
    private boolean payed;

    @OneToMany(mappedBy = "reservation",fetch=FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    private Collection<Room> rooms = new ArrayList<>();


    public Integer getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Integer reservation_id) {
        this.reservation_id = reservation_id;
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

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }

}
