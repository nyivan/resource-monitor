# Resource Monitoring Service
## Requirements

Design and develop an operating system resource monitoring application.

Requirements.
Collect system resources every 30 seconds. Your application should be able to provide current state information along with historical data for the past 24h.
Gather and expose CPU and memory consumption at the minimum. I/O operations, network traffic, disk usage etc. are an advantage.
Presenting the results to the user is up to you. Options include, but are not limited to:
Graphical representation
Text representation
Store the results to a database
Create a client server implementation.


Use an operating system of your choice.
You can use one of these languages - Java, C#, C++, Python.

## Prerequisites for the current solution
* A running postgres server is needed for this solution

## How to start the service
#### run ./gradlew bootRun
#### The service's REST api will be exposed through http port 8080

## Sample API
* curl http://localhost:8080/api/memory
* curl http://localhost:8080/api/cpu