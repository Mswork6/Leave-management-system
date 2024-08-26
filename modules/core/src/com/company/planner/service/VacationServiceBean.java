package com.company.planner.service;

import com.company.planner.entity.Employee;
import com.company.planner.entity.Vacation;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Service(VacationService.NAME)
public class VacationServiceBean implements VacationService {

    private final DataManager dataManager;

    @Inject
    public VacationServiceBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public boolean isVacationOverlapping(Vacation newVacation) {
        Employee employee = newVacation.getPersonalNumber();
        String position = employee.getPosition();
        String department = employee.getDepartment();
        LocalDateTime startDate = newVacation.getVacationStartDate();
        LocalDateTime endDate = newVacation.getVacationEndDate();

        String queryString = "select v from planner_Vacation v " +
                "join v.personalNumber e " +
                "where e.position = :position and e.department = :department " +
                "and v.id <> :vacationId";

        List<Vacation> vacations = dataManager.load(Vacation.class)
                .query(queryString)
                .parameter("position", position)
                .parameter("department", department)
                .parameter("vacationId", newVacation.getId())
                .list();

        for (Vacation vacation : vacations) {
            LocalDateTime existingStartDate = vacation.getVacationStartDate();
            LocalDateTime existingEndDate = vacation.getVacationEndDate();
            if ((endDate.isAfter(existingStartDate) && (startDate.isBefore(existingEndDate))) ||
                    (startDate.equals(existingEndDate) || endDate.equals(existingStartDate))) {
                return true;
            }
        }
        return false;
    }

}