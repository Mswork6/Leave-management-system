package com.company.planner.web.screens.vacation;

import com.company.planner.service.VacationService;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Vacation;


import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;

@UiController("planner_Vacation.edit")
@UiDescriptor("vacation-edit.xml")
@EditedEntityContainer("vacationDc")
@LoadDataBeforeShow
public class VacationEdit extends StandardEditor<Vacation> {

    @Inject
    private Notifications notifications;
    @Inject
    protected TextField<String> fullNameField;
    @Inject
    protected DateField<LocalDateTime> vacationEndDateField;
    @Inject
    protected DateField<LocalDateTime> vacationStartDateField;
    @Inject
    protected TextField<Integer> durationField;
    @Inject
    private VacationService vacationService;

    @Subscribe
    protected void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Vacation newVacation = getEditedEntity();
        if (vacationService.isVacationOverlapping(newVacation)) {
            event.preventCommit();
            notifications.create()
                    .withCaption("Ошибка")
                    .withDescription("Даты отпусков пересекаются с существующими отпусками.")
                    .withType(Notifications.NotificationType.ERROR)
                    .show();
        }
    }

    @Subscribe("durationField")
    protected void onDurationFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        updateVacationEndDate();

    }

    @Subscribe("vacationStartDateField")
    protected void onVacationStartDateFieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        updateVacationEndDate();
    }

    private void updateVacationEndDate() {
        if (vacationStartDateField.getValue() != null && durationField.getValue() != null) {
            LocalDateTime endDate = vacationStartDateField.getValue().plusDays(durationField.getValue() - 1)
                    .withHour(23).withMinute(59);
            vacationEndDateField.setValue(endDate);
        }
    }

}