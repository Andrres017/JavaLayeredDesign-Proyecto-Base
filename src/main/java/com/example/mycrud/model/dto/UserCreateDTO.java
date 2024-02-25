package com.example.mycrud.model.dto;

public class UserCreateDTO {
    private String fullName;
    private Integer idNumber;
    // Otros campos necesarios para crear un usuario
    // Getters y setters

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
}
