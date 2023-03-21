FROM postgres:15.2-alpine
MAINTAINER MaxStrybuk

ADD /fitness_studio/dml/01.add_all.sql /docker-entrypoint-initdb.d/
ADD /fitness_studio/ddl/00.create_all.sql /docker-entrypoint-initdb.d/

