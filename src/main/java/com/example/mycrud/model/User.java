package com.example.mycrud.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;

    private Integer idNumber;

    public User() {

    }

    public User(String fullName, Integer idNumber) {
        this.fullName = fullName;
        this.idNumber = idNumber;
    }

    public User(String fullName, Integer idNumber, Integer id) {
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
