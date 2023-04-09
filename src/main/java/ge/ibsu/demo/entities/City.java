package ge.ibsu.demo.entities;

import jakarta.persistence.*;
@Entity
@Table(name="city")
public class City {
    @Id
    @SequenceGenerator(name="city_city_id_seq", sequenceName = "city_city_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="city_city_id_seq")
    @Column(name="city_id")
    private long cityId;
    @Column(name="city")
    private String city;




    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
