package com.company.planner.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Table(name = "PLANNER_VACATION")
@Entity(name = "planner_Vacation")
@NamePattern("%s|id")
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

    @Column(name = "VACATION_START_DATE", nullable = false)
    @NotNull
    private LocalDateTime vacationStartDate;

    @Column(name = "VACATION_END_DATE")
    private LocalDateTime vacationEndDate;

    public void setVacationStartDate(LocalDateTime vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public LocalDateTime getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationEndDate(LocalDateTime vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getVacationEndDate() {
        return vacationEndDate;
    }

    public Employee getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Employee personalNumber) {
        this.personalNumber = personalNumber;
    }

}