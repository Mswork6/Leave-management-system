alter table PLANNER_VACATION add column FULL_NAME varchar(255) ^
update PLANNER_VACATION set FULL_NAME = '' where FULL_NAME is null ;
alter table PLANNER_VACATION alter column FULL_NAME set not null ;
