package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private int beds;

    private String name;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    @JoinColumn(name="reservation_id")
    private Reservation reservation = new Reservation();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
