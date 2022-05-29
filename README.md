# dotpay

Java 8
---
SpringBoot 2.5.3
---
Maven 3.6.3
---

RUNNING THE APPLICATION
---
1. cd to the directory containing the pom.xml file and run the command below:
mvn clean install package

2. A target directory would be generated. cd into the target directory and run the command below to run the application.

java -jar dotpay-0.0.1-SNAPSHOT.jar --duration=hourly --limit=100 --start=2022-01-01.00:00:11 --accessFile=user_access.txt

To specify the port add the server.port argument like below:
java -jar dotpay-0.0.1-SNAPSHOT.jar --server.port=8084 --duration=hourly --limit=100 --start=2022-01-01.00:00:11 --accessFile=user_access.txt

dotpay-0.0.1-SNAPSHOT.jar => the generated jar file to run
--server.port => specify the port for the application to run on
--start => specify the start date and time to query the user_access_log table
--accessFile => for portability the file should be placed inside the resource folder and only the file name specified for this argument.
--duration => specifies if data should be queried for 1 hour span or for 1 day span.


