# Используем базовый образ с JDK 17
FROM eclipse-temurin:17-jre-alpine

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем .jar файл приложения в контейнер
COPY target/SecurityOTP-1.0.jar /app/SecurityOTP-1.0.jar

# Устанавливаем команду для запуска приложения
CMD ["sh", "-c", "java -jar /app/SecurityOTP-1.0.jar"]