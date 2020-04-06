FROM postgres:10-alpine

ENV PYTHONHTTPSVERIFY=0

RUN apk add --no-cache openssl curl py-pip postgresql-dev make gcc musl-dev && \
     pip install pgxnclient && pgxn install temporal_tables
	
ADD CreateDB.sql /docker-entrypoint-initdb.d/