alter table PLANNER_EMPLOYEE alter column PERSONAL_NUMBER rename to PERSONAL_NUMBER__U33126 ^
-- alter table PLANNER_EMPLOYEE add column PERSONAL_NUMBER varchar(255) ^
-- update PLANNER_EMPLOYEE set PERSONAL_NUMBER = <default_value> ;
-- alter table PLANNER_EMPLOYEE alter column PERSONAL_NUMBER set not null ;
alter table PLANNER_EMPLOYEE add column PERSONAL_NUMBER varchar(255) ;
