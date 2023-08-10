FROM mysql:latest


ENV MYSQL_DATABASE=BOOKQUEST \
    MYSQL_ROOT_PASSWORD=helloworld

COPY books.sql /docker-entrypoint-initdb.d

    
EXPOSE 3306