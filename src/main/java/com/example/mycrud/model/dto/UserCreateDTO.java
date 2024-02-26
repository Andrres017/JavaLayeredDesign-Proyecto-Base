package com.example.mycrud.model.dto;

import jakarta.validation.constraints.*;

public class UserCreateDTO {
    @NotBlank
    @Size(max = 255, min = 5)
    private String fullName;
    @NotNull
    private Integer idNumber;

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
