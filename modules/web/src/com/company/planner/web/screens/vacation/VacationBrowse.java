package com.company.planner.web.screens.vacation;

import com.company.planner.web.screens.vacationInfo.VacationInfo;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Vacation;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UiController("planner_Vacation.browse")
@UiDescriptor("vacation-browse.xml")
@LookupComponent("vacationsTable")
@LoadDataBeforeShow
public class VacationBrowse extends StandardLookup<Vacation> {
    @Inject
    protected Calendar<LocalDateTime> vacationsCalendar;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    protected GroupTable<Vacation> vacationsTable;

    private LocalDateTime currentStartDate;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Subscribe
    public void onInit(InitEvent event) {
        currentStartDate = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        updateCalendar();

        addDateOnlyColumnGenerator("vacationStartDate");
        addDateOnlyColumnGenerator("vacationEndDate");
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
        LocalDateTime endDate = currentStartDate.plusMonths(1).minusDays(1);
        vacationsCalendar.setStartDate(currentStartDate);
        vacationsCalendar.setEndDate(endDate);
    }

    private void addDateOnlyColumnGenerator(String columnId) {
        vacationsTable.addGeneratedColumn(columnId, entity -> {
            LocalDateTime dateTime = entity.getValue(columnId);
            if (dateTime != null) {
                return new Table.PlainTextCell(dateTime.toLocalDate().format(DATE_FORMATTER));
            }
            return new Table.PlainTextCell("");
        });
    }

}