# ticket-handler

## Build:
maven clean package futtatása a root pom.xml file-on.

## Run:
docker-compose up --build

## Adatbázisok újra inicializálása:
docker-compose down

## Adatbázis elérések:

### CORE-DB
host: localhost\
port: 51037\
database: core\
username: core\
password: core

### PARTNER-DB
host: localhost\
port: 51033\
database: partner\
username: partner\
password: partner
