package ge.ibsu.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="address")
public class Address {
    @Id
    @SequenceGenerator(name="address_address_id_seq", sequenceName = "address_address_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="address_address_id_seq")
    @Column(name="address_id")
    private long addressId;
    @Column(name="address")
    private String address;

    @JoinColumn(name="city_id")
    @ManyToOne(fetch= FetchType.LAZY)
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
