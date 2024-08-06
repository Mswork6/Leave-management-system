-- begin PLANNER_VACATION
create table PLANNER_VACATION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    EMPLOYEE varchar(36) not null,
    FULL_NAME varchar(255),
    VACATION_START_DATE date not null,
    DURATION integer not null,
    --
    primary key (ID)
)^
-- end PLANNER_VACATION
-- begin PLANNER_EMPLOYEE
create table PLANNER_EMPLOYEE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PERSONAL_NUMBER varchar(255) not null,
    FULL_NAME varchar(255) not null,
    POSITION_ varchar(255) not null,
    DEPARTMENT varchar(255),
    EMAIL varchar(255),
    PHONE_NUMBER varchar(255),
    --
    primary key (ID)
)^
-- end PLANNER_EMPLOYEE
