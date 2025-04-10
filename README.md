```sh
docker run --name OTP_security -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=security -p 5432:5432 -d postgres:13
```
```shell
docker-compose -p security_otp up --build
```