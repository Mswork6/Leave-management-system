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
    VACATION_START_DATE date not null,
    DURATION integer not null,
    --
    primary key (ID)
);