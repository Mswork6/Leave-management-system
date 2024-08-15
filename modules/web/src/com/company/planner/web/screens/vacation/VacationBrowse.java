package com.company.planner.web.screens.vacation;

import com.company.planner.web.screens.vacationInfo.VacationInfo;
import com.haulmont.cuba.gui.ScreenBuilders;
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
    protected Calendar<LocalDate> vacationsCalendar;
    @Inject
    private ScreenBuilders screenBuilders;
    private LocalDate currentStartDate;

    @Subscribe
    public void onInit(InitEvent event) {
        currentStartDate = LocalDate.now().withDayOfMonth(1);
        vacationsCalendar.setFirstDayOfWeek(java.util.Calendar.MONDAY);
        updateCalendar();
    }

    @Subscribe("vacationsCalendar")
    protected void onCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<LocalDate> event) {
        VacationInfo screen = screenBuilders.screen(this)
                .withScreenClass(VacationInfo.class)
                .withLaunchMode(OpenMode.DIALOG)
                .build();

        Vacation vacation = (Vacation) event.getEntity();

        // Передаем данные в окно
        assert vacation != null;
        screen.setPersonalNumberValue(vacation.getPersonalNumber().getPersonalNumber());
        screen.setFullNameValue(vacation.getPersonalNumber().getFullName());
        screen.setStartDateValue(vacation.getVacationStartDate());
        screen.setEndDateValue(vacation.getVacationEndDate());
        screen.setDurationValue(String.valueOf(vacation.getDuration()));


        screen.show();
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