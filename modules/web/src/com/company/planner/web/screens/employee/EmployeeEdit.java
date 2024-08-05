package com.company.planner.web.screens.employee;

import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Employee;

@UiController("planner_Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
@LoadDataBeforeShow
public class EmployeeEdit extends StandardEditor<Employee> {
}