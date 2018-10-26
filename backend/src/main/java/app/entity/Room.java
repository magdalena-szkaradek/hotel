package app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private int beds;

    private int price;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBeds() {
        return beds;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
