package com.company.planner.service;

import com.company.planner.entity.Vacation;

public interface VacationService {
    String NAME = "planner_VacationService";

    boolean isVacationOverlapping(Vacation newVacation);
}