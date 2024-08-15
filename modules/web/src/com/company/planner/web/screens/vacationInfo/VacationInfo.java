package com.company.planner.web.screens.vacationInfo;

import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UiController("event-window")
@UiDescriptor("vacation-info.xml")
public class VacationInfo extends Screen {
    @Inject
    private Label<String> personalNumberValue;
    @Inject
    private Label<String> fullNameValue;
    @Inject
    private Label<String> startDateValue;
    @Inject
    private Label<String> endDateValue;
    @Inject
    private Label<String> durationValue;

    private final String timePattern = "dd.MM.yyyy";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timePattern);

    public void setPersonalNumberValue(String caption) {
        personalNumberValue.setValue(caption);
    }

    public void setFullNameValue(String description) {
        fullNameValue.setValue(description);
    }


    public void setStartDateValue(LocalDateTime startDate) {
        startDateValue.setValue(startDate.format(dateTimeFormatter));
    }

    public void setEndDateValue(LocalDateTime endDate) {
        endDateValue.setValue(endDate.format(dateTimeFormatter));
    }

    public void setDurationValue(String duration) {
        durationValue.setValue(duration);
    }

    @Subscribe("closeButton")
    protected void onCloseButtonClick(Button.ClickEvent event) {
        close(WINDOW_CLOSE_ACTION);
    }
}
