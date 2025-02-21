# 1. OpenJDK 17 기반 이미지 사용
FROM openjdk:17-jdk

# 2. 컨테이너에 볼륨 설정
VOLUME /lms

# 3. JAR 파일 경로 설정
ARG JAR_FILE=build/libs/lmsSite.jar

# 4. JAR 파일을 컨테이너로 복사
COPY ${JAR_FILE} app.jar

# 5. 애플리케이션 실행 명령어 설정
ENTRYPOINT ["java", "-jar", "app.jar"]

