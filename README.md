# rest-service
A project for creating and consuming REST Services using Swagger yaml API definitions.
This is intended as a test area to try out API behaviours and understand the build process

https://swagger.io/docs/specification/v3_0/about/

## Build
`mvn clean install` to build

To run from command line. Change to `service` directory. 

## Run
From command-line

Run: `mvn sprint-boot:run`

From IDE. I use IntelliJ. Run

* Classpath : `service`
* Main class: `jonathan.home.service.ServiceApplication`
* Working directory : Path to `rest-service`

## Sample request body

### test2/

``` json
{
  "action": {
    "actiontype": "Implementation",
    "InputVal": "Test",
    "Metrics": [
       { 
         "name": "Metric1"
       },
       {
         "name": "Metric2",
         "value": "a",
         "property": 2
       }
     ]
  }
}
```
## Swagger and docs

* http://localhost:8080/swagger-ui/index.htm
* http://localhost:8080/v3/api-docs 

## Python client

The Python API is compiled to `rest-service/python`. The python client `client.py` can be used to call the service

* Use Miniconda
* Create a new environment
* Install requests

