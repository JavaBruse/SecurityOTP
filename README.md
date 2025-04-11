# Проект отправки OTP кодов

### Запуск:

- Создаем базу данных в докере.

```sh
docker run --name OTP_security -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=security -p 5432:5432 -d postgres:13
```

#### Перед началом сборки проекта, требуется заполнить настройки email, telegram, smpp
```yaml
mail:
  smtp:
    host: smtp.gmail.com
    port: 587
    auth: true
    starttls:
      enable: true
  username:
    USERNAME
  password:
    PASS
smpp:
  host: smpp.example.com
  port: 2775
  systemId: yourSystemId
  password: yourPassword
  systemType: ""
  sourceAddr: "YOUR_SENDER_ID"
  addrTon: 1
  addrNpi: 1

telegram:
  token: YOUR_TOKEN
```

- Запускаем проектa через командную строку:
```shell
mvn clean install
java -jar .\target\SecurityOTP-1.0.jar 
```
Для запуска в докер контейнере:
Перед сборкой, требуется изменить адреса, тестировал только на intellijIdea.
```shell
mvn clean install
docker-compose -p security_otp up --build
```

### Использование:

http://localhost:8189/auth/sign-up
запрос на регистрацию

```json
{
  "username": "string1243",
  "email": "string33@mail.con",
  "password": "string124124",
  "telegram": "@123231",
  "phone": "+79999999999"
}
```

http://172.16.1.33:8189/auth/sign-in
запрос на авторизацию

```json
{
  "username": "string33@mail.con",
  "password": "string33@mail.con"
}
```

в таком виде    "token": "
eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwiaWQiOiJlNWMxOGY2ZC1mYWZjLTRmMDQtYTJiYi1jM2FjM2YxOTI1ZjgiLCJlbWFpbCI6InN0cmluZzMzQG1haWwuY29uIiwic3ViIjoic3RyaW5nMzNAbWFpbC5jb24iLCJpYXQiOjE3NDQyOTA1MzIsImV4cCI6MTc0NDQzNDUzMn0.Xp6Px5G9UAX1rn0KguWoV88kD4IWvoNafMg_O5vhWdw"
его требуется добавить в headers поле "Authorization".

## Далее по пунктам:

- Структура приложения соответствует требованиям — 5 баллов;
    * Соответсвует.
- Используется система сборки Maven или Gradle — 5 баллов;
    * используется Maven
- Реализован минимальный функционал основных операций приложения без токенной аутентификации и авторизации — 17 баллов;
    * Реализован весь функционал с токенной аутентификации и авторизацией, согластно условия.
- Запросы к приложению имеют разграничение по ролям администратора и обычного пользователя — 5 баллов;
    * Есть две роли ROLE_USER, ROLE_ADMIN, при этом админ может быть только один.
  ```java
  .requestMatchers("/auth/**").permitAll() // не авторизованные могут только зарегестрироваться или авторизоваться.
  .requestMatchers("/endpoint", "/admin/**").hasRole("ADMIN") // у пользователя с правами админа endpoint /admin
  .anyRequest().authenticated()) // остальные авторизованные пользователи могут получить доступ везде. 
  ```
- Для разработки API был использован пакет com.sun.net.httpserver или Spring MVC — 6 баллов;
    * Использован Spring security, Spring data Jpa, со всеми настройками безопасности и пр.
- Было реализовано минимальное покрытие логами каждого запроса к API — 3 балла.
    * Было реализовано минимальное покрытие логами с помощью Logger.
- Реализован механизм рассылки OTP-кодов по почте — 5 баллов.
    * Реализована
    * Отправка post запроса на http://localhost:8189/user/otp-gen

```json
{
  "operationId": "code_Unique",
  "deliveryType": "EMAIL"
}
  ```

- Реализован механизм рассылки OTP-кодов через эмулятор SMPP — 5 баллов.
    * Реализована
    * Отправка post запроса на http://localhost:8189/user/otp-gen

```json
{
  "operationId": "code_Unique",
  "deliveryType": "SMS"
}
  ```

- Реализован механизм рассылки OTP-кодов через Telegram — 5 баллов.
    * Реализована
    * Отправка post запроса на http://localhost:8189/user/otp-gen

```json
{
  "operationId": "code_Unique",
  "deliveryType": "TELEGRAM"
}
  ```

- Реализован механизм сохранения OTP-кодов в файл — 5 баллов.
    * Реализована
    * Отправка post запроса на http://localhost:8189/user/otp-gen

```json
{
  "operationId": "code_Unique",
  "deliveryType": "FILE"
}
  ```
- Реализован механизм токенной аутентификации и авторизации — 5 баллов.
    * Реализовано.
- Реализовано подробное покрытие всех запросов к API логами — 3 балла.
    * Реализовано покрытие логами спринг и Logger, всей работы приложения.

- Проверка валидности кода:
    - отправляем запрос на: http://localhost:8189/user/otp-valid
        * В теле запроса пишем:

  ```json
  {
    "operationId": "123456",
    "code": "EXxqdG"
  }
  ```


- Реализация изменения ОТП опций:
    - отправялем запрос на:  http://localhost:8189/admin/editOptionsOTP
        * в теле запрос пишем:

```json
{
  "timeLife": 20000,
  "countChars": 4
}
```