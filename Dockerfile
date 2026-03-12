FROM eclipse-temurin:17-jre

ADD data-server/target/data-server-1.0-SNAPSHOT.jar /app.jar

# 设置时区
ENV TZ=Japan/Tokyo

# Java 运行时参数
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

# mvn clean package
# docker build -t yexca/data-element:v1.3 .
