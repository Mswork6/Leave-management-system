alter table PLANNER_VACATION add constraint FK_PLANNER_VACATION_ON_EMPLOYEE foreign key (EMPLOYEE) references PLANNER_EMPLOYEE(ID);
create index IDX_PLANNER_VACATION_ON_EMPLOYEE on PLANNER_VACATION (EMPLOYEE);
