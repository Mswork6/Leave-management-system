package com.company.planner.web.screens.employee;

import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Employee;
import org.slf4j.Logger;

import javax.inject.Inject;

@UiController("planner_Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
@LoadDataBeforeShow
public class EmployeeEdit extends StandardEditor<Employee> {

    @Inject
    private TextField<String> phoneNumberField;

    protected static final Logger log = org.slf4j.LoggerFactory.getLogger(EmployeeEdit.class);


    @Subscribe("phoneNumberField")
    protected void onPhoneNumberFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String newValue = event.getValue();
        log.info(newValue);
        if (newValue != null) {
            String validatedValue = newValue.replaceAll("[^0-9]", "");
            if (!newValue.equals(validatedValue)) {
                log.info("In loop");
                log.info(validatedValue);
                phoneNumberField.setValue(validatedValue);
            }
        }
    }


}
