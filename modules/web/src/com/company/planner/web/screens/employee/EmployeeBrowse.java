package com.company.planner.web.screens.employee;

import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Employee;

@UiController("planner_Employee.browse")
@UiDescriptor("employee-browse.xml")
@LookupComponent("employeesTable")
@LoadDataBeforeShow
public class EmployeeBrowse extends StandardLookup<Employee> {
}