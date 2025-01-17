package com.company.planner.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Table(name = "PLANNER_EMPLOYEE")
@Entity(name = "planner_Employee")
@NamePattern("%s|personalNumber")
public class Employee extends StandardEntity {
    private static final long serialVersionUID = -4128883590624779007L;

    @Column(name = "PERSONAL_NUMBER", nullable = false, unique = true)
    @Pattern(message = "Табельный номер должен состоять только из цифр", regexp = "[0-9]+")
    private String personalNumber;

    @NotNull
    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @NotNull
    @Column(name = "POSITION_", nullable = false)
    private String position;

    @Column(name = "DEPARTMENT", nullable = false)
    @NotNull
    private String department;

    @Column(name = "EMAIL")
    @Email
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}