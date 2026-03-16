# builder
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /builder
# cache
COPY pom.xml .
COPY data-server/pom.xml data-server/
COPY data-pojo/pom.xml data-pojo/
COPY data-common/pom.xml data-common/
RUN mvn dependency:go-offline -B
# pacakge
COPY . .
RUN mvn clean package -DskipTests

# running
FROM eclipse-temurin:17-jre
# set TZ
ENV TZ=Asia/Tokyo
# copy file
COPY --from=builder /builder/data-server/target/data-server-1.0-SNAPSHOT.jar /app.jar
# cmd run
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

# docker build -t yexca/data-element:v1.3 .