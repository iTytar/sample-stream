# Sample Stream

To build run
>mvn clean package

To start produser run
>mvn spring-boot:run

>curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"fistName":"Igor","lastName":"Tytar"}' \
  http://localhost:8080/api/persons

for Windows
>curl --header "Content-Type: application/json" --request POST --data {\"firstName\":\"Igor\",\"lastName\":\"Tytar\"} http://localhost:8080/api/persons
