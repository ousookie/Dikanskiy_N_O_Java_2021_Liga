FROM postgres:alpine

ADD init_user_db.sh /docker-entrypoint-initdb.d
ADD V1__init_schema.sql /docker-entrypoint-initdb.d

