FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
ENV DATABASE_URL jdbc:postgresql://dpg-clouf31oh6hc73bolf1g-a.oregon-postgres.render.com:5432/schedule_jw8q
ENV DATABASE_USER bestuser
ENV DATABASE_PASSWORD 05FWwngSofJgh67nwFUknQioZLozaybx
EXPOSE 8080
