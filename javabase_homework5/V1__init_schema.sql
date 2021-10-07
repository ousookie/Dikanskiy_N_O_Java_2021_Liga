create type gen as enum ('м', 'ж');

create table subject
(
    id   bigint primary key,
    name varchar(255) not null
);

create table school
(
    id      bigint primary key,
    name    varchar(255) not null,
    address varchar(255) not null
);

create table student
(
    id        bigint primary key,
    name      varchar(255) not null,
    age       varchar(255) not null,
    gender    gen,
    school_id bigint references school
);

create table student_subjects
(
    student_id bigint references student,
    subject_id bigint references subject,
    primary key (student_id, subject_id)
);

create table teacher
(
    id        bigint primary key,
    name      varchar(255) not null,
    age       varchar(255) not null,
    gender    gen,
    school_id bigint references school
);

create table teacher_subjects
(
    teacher_id bigint references teacher,
    subject_id bigint references subject,
    primary key (teacher_id, subject_id)
);

