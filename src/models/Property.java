package models;

import java.util.Objects;

public class Property {
    //PROPERTIES
    private Long id; //PRIMARY KEY
    private String name;
    private Double longitude;
    private Double latitude;
    private Double surface; //HECTARES
    private String locality;
    private String province;
    //CONSTRUCTOR
    public Property(){

    }
    public Property(Long id, String name, Double longitude, Double latitude, Double surface, String locality, String province) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.surface = surface;
        this.locality = locality;
        this.province = province;
    }
    //GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    //TO STRING

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Property{");
        sb.append("id=").append(id);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", surface=").append(surface);
        sb.append(", locality='").append(locality).append('\'');
        sb.append(", province='").append(province).append('\'');
        sb.append('}');
        return sb.toString();
    }
    //EQUALS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(id, property.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
