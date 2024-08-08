package com.company.planner.web.screens.vacation;

import com.company.planner.entity.Employee;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Vacation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@UiController("planner_Vacation.edit")
@UiDescriptor("vacation-edit.xml")
@EditedEntityContainer("vacationDc")
@LoadDataBeforeShow
public class VacationEdit extends StandardEditor<Vacation> {

    protected static final Logger log = LoggerFactory.getLogger(VacationEdit.class);
    @Inject
    private DataManager dataManager;
    @Inject
    private Notifications notifications;
    @Inject
    protected TextField<String> fullNameField;
    @Inject
    protected DateField<LocalDate> vacationEndDateField;
    @Inject
    protected DateField<LocalDate> vacationStartDateField;
    @Inject
    protected TextField<Integer> durationField;
    
    
//    @Subscribe("commitAndCloseBtn")
//    protected void onCommitAndCloseBtnClick(Button.ClickEvent event) {
//        if (!isVacationOverlapping()) {
//            log.info("Brawo, It's Charley, sector is clear");
//
//        } else {
//            notifications.create()
//                    .withCaption("Ошибка")
//                    .withDescription("Даты отпусков пересекаются с существующими отпусками.")
//                    .withType(Notifications.NotificationType.ERROR)
//                    .show();
//            log.info("Vacation dates are overlapping with existing vacations.");
//        }
//    }

    @Subscribe
    protected void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        if (isVacationOverlapping()) {
            event.preventCommit();
            notifications.create()
                    .withCaption("Ошибка")
                    .withDescription("Даты отпусков пересекаются с существующими отпусками.")
                    .withType(Notifications.NotificationType.ERROR)
                    .show();
        }
    }

    private boolean isVacationOverlapping() {
        Vacation newVacation = getEditedEntity();
        Employee employee = newVacation.getPersonalNumber();
        String position = employee.getPosition();
        String department = employee.getDepartment();
        LocalDate startDate = newVacation.getVacationStartDate();
        LocalDate endDate = startDate.plusDays(newVacation.getDuration());

        // Загружаем все отпуска сотрудников с той же должностью и отделом
        String queryString = "select v from planner_Vacation v " +
                "join v.personalNumber e " +
                "where e.position = :position and e.department = :department";

        List<Vacation> vacations = dataManager.load(Vacation.class)
                .query(queryString)
                .parameter("position", position)
                .parameter("department", department)
                .list();

         //Проверяем пересечение дат
        for (Vacation vacation : vacations) {
            LocalDate existingStartDate = vacation.getVacationStartDate();
            LocalDate existingEndDate = existingStartDate.plusDays(vacation.getDuration());
            log.info(String.valueOf(existingEndDate));
            if ((endDate.isAfter(existingStartDate) && (startDate.isBefore(existingEndDate))) ||
                    (startDate.equals(existingEndDate) || endDate.equals(existingStartDate))) {
                return true;
            }
        }
        return false;
        
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
            LocalDate endDate = vacationStartDateField.getValue().plusDays(durationField.getValue());
            vacationEndDateField.setValue(endDate);
        }
    }
    

    // --------------------------------------------------------------------------------------- VER 1.1

//    @Subscribe("commitAndCloseBtn")
//    protected void onCommitAndCloseBtnClick(Button.ClickEvent event) {
//        loadDataFromDatabase();
//
//    }
//
//    private void loadDataFromDatabase() {
//        String queryString = "select e from planner_Vacation e";
//        List<Vacation> results = dataManager.load(Vacation.class)
//                .query(queryString)
//                .list();
//        // Обработка результатов
//        for (Vacation vacation : results) {
//            log.info(vacation.getDuration().toString());
//            log.info(vacation.getVacationStartDate().toString());
//        }
//    }

}