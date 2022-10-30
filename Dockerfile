FROM gradle:7.4.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test

FROM amazoncorretto:17-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/kanban-board.jar
ENTRYPOINT ["java", "-jar", "/app/kanban-board.jar"]
#COPY --from=build /home/gradle/src/build/libs/*.jar /app/
#ENTRYPOINT ["java", "-jar", "/app/kanban-0.0.1-SNAPSHOT.jar"]