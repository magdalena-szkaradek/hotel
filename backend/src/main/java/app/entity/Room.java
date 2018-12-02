package app.entity;

import javax.persistence.*;


@Entity
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private int beds;

    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
}
