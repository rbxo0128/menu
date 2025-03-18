FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

# 모든 프로젝트 파일 복사
COPY . .

# shade 플러그인을 사용하여 의존성이 포함된 uber-jar 빌드
RUN mvn clean package -DskipTests

# 2. 런타임 스테이지
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# 빌드된 fat/uber JAR 파일 복사
COPY --from=builder /app/target/*-SNAPSHOT.jar app.jar

# 환경 변수 설정 - Render에서 설정한 환경변수 사용
ENV TOKEN=${TOKEN}
ENV GEMINI_KEY=${GEMINI_KEY}

# 컨테이너 실행 시 JAR 실행
CMD ["java", "-jar", "app.jar"]