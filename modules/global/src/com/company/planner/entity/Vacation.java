package com.company.planner.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Table(name = "PLANNER_VACATION")
@Entity(name = "planner_Vacation")
public class Vacation extends StandardEntity {
    private static final long serialVersionUID = -8586135482199209593L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EMPLOYEE")
    private Employee personalNumber;

    @MetaProperty
    public String getFullName() {
        return personalNumber != null ? personalNumber.getFullName() : "";
    }

    @NotNull
    @Column(name = "DURATION", nullable = false)
    @Positive
    private Integer duration;

    @NotNull
    @Column(name = "VACATION_START_DATE", nullable = false)
    private LocalDate vacationStartDate;

    @Column(name = "VACATION_END_DATE")
    private LocalDate vacationEndDate;

    public void setVacationEndDate(LocalDate vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }


    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(LocalDate vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public LocalDate getVacationEndDate() {
        return vacationEndDate;
    }


    public Employee getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Employee personalNumber) {
        this.personalNumber = personalNumber;
    }

}