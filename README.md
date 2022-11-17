# Resource Monitoring Service
Functional requirements

* Gather and persist the system's resources snapshots (cpu and memory at minimum) on each 30 seconds
* Expose a historical view of the resources together with the current state of the resource
* Use any language and framework of your choice

## Prerequisites
* A running postgres server is needed for this solution 

## How to start the service
#### run ./gradlew bootRun
#### The service's REST api will be exposed through http port 8080

## Sample API
* curl http://localhost:8080/api/memory
* curl http://localhost:8080/api/cpu