package app.entity;

public class RoomDTO {

    private Integer id;

    private int beds;

    private Double pricePerNightWithoutSeasoningSystem;

    private Double pricePerNightWithSeasoningSystem;

    private String name;

    private Integer extraPaidDays;

    private Integer normalPaidDays;

    private Integer seasoningPercentage;

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

    public Double getPricePerNightWithSeasoningSystem() {
        return pricePerNightWithSeasoningSystem;
    }

    public void setPricePerNightWithSeasoningSystem(Double pricePerNightWithSeasoningSystem) {
        this.pricePerNightWithSeasoningSystem = pricePerNightWithSeasoningSystem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getPricePerNightWithoutSeasoningSystem() {
        return pricePerNightWithoutSeasoningSystem;
    }

    public void setPricePerNightWithoutSeasoningSystem(Double pricePerNightWithoutSeasoningSystem) {
        this.pricePerNightWithoutSeasoningSystem = pricePerNightWithoutSeasoningSystem;
    }

    public Integer getExtraPaidDays() {
        return extraPaidDays;
    }

    public void setExtraPaidDays(Integer extraPaidDays) {
        this.extraPaidDays = extraPaidDays;
    }

    public Integer getNormalPaidDays() {
        return normalPaidDays;
    }

    public void setNormalPaidDays(Integer normalPaidDays) {
        this.normalPaidDays = normalPaidDays;
    }

    public Integer getSeasoningPercentage() {
        return seasoningPercentage;
    }

    public void setSeasoningPercentage(Integer seasoningPercentage) {
        this.seasoningPercentage = seasoningPercentage;
    }
}
