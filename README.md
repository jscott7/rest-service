# rest-service
A project for creating and consuming REST Services

## Useful information
`mvn clean install` to build

To run from command line. Change to `service` directory. 

Run: `mvn sprint-boot:run`

## Calling Test endpoint

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


