package com.company.planner.web.screens.vacation;

import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Vacation;

import javax.inject.Inject;
import java.time.LocalDate;

@UiController("planner_Vacation.browse")
@UiDescriptor("vacation-browse.xml")
@LookupComponent("vacationsTable")
@LoadDataBeforeShow
public class VacationBrowse extends StandardLookup<Vacation> {
    @Inject
    private Button nextMonthBtn;
    @Inject
    private Button prevMonthBtn;
    @Inject
    protected Calendar<LocalDate> vacationsCalendar;
    private LocalDate currentStartDate;

    @Subscribe
    public void onInit(InitEvent event) {
        currentStartDate = LocalDate.now().withDayOfMonth(1);
        vacationsCalendar.setFirstDayOfWeek(java.util.Calendar.MONDAY);
        updateCalendar();
    }

    @Subscribe("nextMonthBtn")
    public void onNextMonthBtnClick(Button.ClickEvent event) {
        currentStartDate = currentStartDate.plusMonths(1);
        updateCalendar();
    }

    @Subscribe("prevMonthBtn")
    public void onPrevMonthBtnClick(Button.ClickEvent event) {
        currentStartDate = currentStartDate.minusMonths(1);
        updateCalendar();
    }

    private void updateCalendar() {
        LocalDate endDate = currentStartDate.plusMonths(1).minusDays(1);
        vacationsCalendar.setStartDate(currentStartDate);
        vacationsCalendar.setEndDate(endDate);
    }

}