update PLANNER_EMPLOYEE set DEPARTMENT = '' where DEPARTMENT is null ;
alter table PLANNER_EMPLOYEE alter column DEPARTMENT set not null ;
