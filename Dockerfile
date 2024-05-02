FROM openjdk:17

ADD data-server/target/data-server-1.0-SNAPSHOT.jar /app.jar

# 设置时区为东八区
ENV TZ=Asia/Shanghai

# Java 运行时参数
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

# docker build -t yexca/data-element:v1.2 .
