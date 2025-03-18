FROM maven:3.8.5-openjdk-17

WORKDIR /app

# 모든 프로젝트 파일 복사
COPY . .

# Maven으로 패키징 (shade 플러그인 사용)
RUN mvn clean package -DskipTests

# 명시적으로 메인 클래스를 지정하여 JAR 실행
CMD ["java", "-cp", "target/discord-1.0-SNAPSHOT.jar", "Main"]