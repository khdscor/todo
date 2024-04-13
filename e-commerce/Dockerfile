FROM gradle:7.6-jdk as builder

WORKDIR /app
COPY ./ ./
RUN gradle clean build --no-daemon
# 빌더 이미지에서 애플리케이션 빌드

# APP
FROM openjdk:17.0-slim
WORKDIR /app 
# 빌더 이미지에서 jar 파일만 복사
COPY --from=builder /app/build/libs/e-commerce-0.0.1-SNAPSHOT.jar .

EXPOSE 8080
# root 대신 nobody 권한으로 실행
USER nobody
ENTRYPOINT ["java", "-jar", "e-commerce-0.0.1-SNAPSHOT.jar"]
