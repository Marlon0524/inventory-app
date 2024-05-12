FROM openjdk:17
VOLUME /tmp
EXPOSE 9595
ADD ./target/inventory-0.0.1-SNAPSHOT.jar inventory-app.jar
ENTRYPOINT ["java", "-jar", "inventory-app.jar"]