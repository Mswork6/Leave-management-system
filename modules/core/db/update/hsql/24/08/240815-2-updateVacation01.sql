alter table PLANNER_VACATION alter column VACATION_START_DATE rename to VACATION_START_DATE__U98682 ^
alter table PLANNER_VACATION alter column VACATION_START_DATE__U98682 set null ;
alter table PLANNER_VACATION add column VACATION_START_DATE timestamp ^
update PLANNER_VACATION set VACATION_START_DATE = current_timestamp where VACATION_START_DATE is null ;
alter table PLANNER_VACATION alter column VACATION_START_DATE set not null ;
