package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reading {
    //PROPERTIES
    private Long id;
    private Double temperature;
    private Double humidity;
    private LocalDateTime moment;
    private Property property;
    //CONSTRUCTOR
    public Reading(){

    }
    public Reading(Long id, Double temperature, Double humidity, LocalDateTime moment, Property property) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.moment = moment;
        this.property = property;
    }
    //GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
    //TO STRING

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Reading{");
        sb.append("id=").append(id);
        sb.append(", temperature=").append(temperature);
        sb.append(", humidity=").append(humidity);
        sb.append(", moment=").append(moment);
        sb.append(", property=").append(property);
        sb.append('}');
        return sb.toString();
    }
    //EQUALS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reading reading = (Reading) o;
        return Objects.equals(id, reading.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
