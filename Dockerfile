FROM node:19.3.0 as frontend
WORKDIR /football-api/frontend/src/app
COPY frontend/package.json .
RUN npm i -g @angular/cli@~10.0.4
RUN npm i
COPY frontend .
RUN ng build --prod

FROM openjdk:17-oracle as backend
ARG JAR_FILE=/api/target/*.jar
COPY ${JAR_FILE} app.jar

FROM mysql:8.0.31
ENV MYSQL_ROOT_PASSWORD=12345678
ENV MYSQL_DATABASE=football
COPY db/changelog /docker-entrypoint-initdb.d/
COPY db/changelog /docker-entrypoint-initdb.d/
EXPOSE 3306

FROM openjdk:17-oracle
COPY --from=frontend /app/frontend/dist/frontend /app/frontend/dist/frontend
COPY --from=backend /app.jar /app.jar
COPY --from=mysql /docker-entrypoint-initdb.d /docker-entrypoint-initdb.d
COPY docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh
CMD ["/docker-entrypoint.sh"]
