package com.espinoza.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;


@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "active",nullable = false)
    private boolean active;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "country",nullable = false)
    private String country;

    @Column(name = "last_name",nullable = false)
    private String last_name;

    @Column(name = "address",nullable = true)
    private String address;

    @Column(name = "email", nullable = false)
    @Email(message = "Email Incorrecto")
    private String email;

    @Column(name = "photo",nullable = true)
    private String photo;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "contract",nullable = true)
    private String contract;

    @Column(name = "state",nullable = true)
    private String state;

    @Column(name = "salary")
    private String salary;

    public Contact() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
