package com.globalLogic.carRide.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.commons.lang.RandomStringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cid")
    private String cid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_no")
    private long phoneNo;

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<BookingEntity> bookingEntityList= new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid() {
        this.cid = RandomStringUtils.randomAlphanumeric(10);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<BookingEntity> getBookingEntityList() {
        return (List<BookingEntity>) bookingEntityList;
    }

    public void setBookingEntityList(List<BookingEntity> bookingEntityList) {
        this.bookingEntityList = bookingEntityList;
    }
}
