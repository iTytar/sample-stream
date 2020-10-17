# Sample Stream

To build run
>mvn clean package

To start zookeeper & kafka & kafka-manager run
>docker-compose up -d

To stop zookeeper & kafka & kafka-manager run
>docker-compose down

To open kafka-manager open in browser http://localhost:9000/
Create cluster with "Cluster Zookeeper Hosts" "zookeeper"


To start produser go to directory "sample-stream-producer" and run
>mvn spring-boot:run

After prompt type comma separated first name and last name of person
>Enter person <FirstName>,<LastName> : Igor,Tytar

To start processor go to directory "sample-stream-processor" and run
>mvn spring-boot:run
 
After prompt type department for person
>Enter department for Person(firstName=Igor, lastName=Tytar) : developer


To start consumer go to directory "sample-stream-consumer" and run
>mvn spring-boot:run

When employee will be produced it will be printed

>Hired: Employee(person=Person(firstName=a, lastName=a), position=1, startDate=Sat Oct 17 21:10:37 GMT+07:00 2020)
