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
    DURATION integer not null,
    VACATION_START_DATE timestamp not null,
    VACATION_END_DATE timestamp,
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
    DEPARTMENT varchar(255) not null,
    EMAIL varchar(255),
    PHONE_NUMBER varchar(255),
    --
    primary key (ID)
)^
-- end PLANNER_EMPLOYEE
