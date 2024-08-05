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
    private Employee employee;

    @NotNull
    @Column(name = "VACATION_START_DATE", nullable = false)
    private LocalDate vacationStartDate;

    @NotNull
    @Column(name = "DURATION", nullable = false)
    @Positive
    private Integer duration;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @MetaProperty(related = {"vacationStartDate", "duration"})
    public LocalDate getVacationEndDate() {
        return (vacationStartDate != null && duration != null) ? vacationStartDate.plusDays(duration) : null;
    }
}