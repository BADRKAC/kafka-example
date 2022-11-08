# Kafka example 

# Technical stack :
* Docker for containerize the application
* spring for Apache Kafka
* spring Web
* Java 17

# Run the project
```
docker-compose up --build
```

# Interact with the API's resources :

POST http://localhost:8080/api/v1/messages
Content-Type: application/json

{
 "message": "Api with Kafka"
}
