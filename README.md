# Swiftlify
a quick commerce java spring boot microservice project 

## Steps to Run the Project:

### Run these commands before to setup the env

docker run -d --name mysql_product_service -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 mysql:8.0

docker exec -it mysql_product_service mysql -u root -p 

type root

CREATE DATABASE productdb;

CREATE DATABASE orderdb;