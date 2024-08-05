package com.company.planner.web.screens.vacation;

import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Vacation;

@UiController("planner_Vacation.edit")
@UiDescriptor("vacation-edit.xml")
@EditedEntityContainer("vacationDc")
@LoadDataBeforeShow
public class VacationEdit extends StandardEditor<Vacation> {
}