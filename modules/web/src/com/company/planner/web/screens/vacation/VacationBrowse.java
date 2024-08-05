package com.company.planner.web.screens.vacation;

import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Vacation;

@UiController("planner_Vacation.browse")
@UiDescriptor("vacation-browse.xml")
@LookupComponent("vacationsTable")
@LoadDataBeforeShow
public class VacationBrowse extends StandardLookup<Vacation> {
}