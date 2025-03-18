FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

# 프로젝트 의존성 다운로드 (캐시 활용)
COPY pom.xml ./ 
RUN mvn dependency:resolve 

# 소스 코드 복사 및 빌드
COPY src ./src
RUN mvn clean package -DskipTests

# 2. 런타임 스테이지 (경량 OpenJDK 사용)
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# 빌드된 JAR 복사
COPY --from=builder /app/target/*.jar bot.jar

# 실행 명령 변경: Main 클래스를 직접 실행
CMD ["java", "-cp", "bot.jar", "Main"]