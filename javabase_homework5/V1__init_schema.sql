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

create table subject_students
(
    subject_id bigint references subject,
    student_id bigint references student,
    primary key (subject_id, student_id)
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

create table subject_teachers
(
    subject_id bigint references subject,
    teacher_id bigint references student,
    primary key (subject_id, teacher_id)
);

create table school_teachers
(
    school_id  bigint references school,
    teacher_id bigint references teacher,
    primary key (school_id, teacher_id)
);

create table school_students
(
    school_id  bigint references school,
    student_id bigint references student,
    primary key (school_id, student_id)
);




