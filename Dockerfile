FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

# 프로젝트 의존성 미리 다운로드하여 캐시 활용
COPY pom.xml ./
RUN mvn dependency:resolve

# 소스 코드 복사 및 빌드
COPY src ./src
# 메인 클래스를 지정하여 매니페스트에 추가하는 플러그인 설정으로 빌드
RUN mvn clean package -DskipTests -Dexec.mainClass="Main"

# 2. 런타임 스테이지 (경량 OpenJDK 사용)
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/target/*.jar bot.jar

# 컨테이너 실행 시 JAR 실행 - 메인 클래스 명시적 지정
CMD ["java", "-jar", "bot.jar", "Main"]