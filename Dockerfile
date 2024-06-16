FROM openjdk:17

WORKDIR /luckyBank


COPY target/LuckyBank-0.0.1-SNAPSHOT.jar /luckyBank/LuckyBank-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "LuckyBank-0.0.1-SNAPSHOT.jar"]
