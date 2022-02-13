## Migrating to microservices

Link to [GitHub docs](https://github.com/billk97/weather-api/blob/master/src/site/markdown/microservices-migration.md)

The application will be split into 3 microservices. UserService, ForecastProviderService and ForecastService.
The following schema shows the 3 microservices.

The communication between the microservices is described in the following schema.

![image](/src/site/markdown/uml/reqs/microservices-comunication.jpg)


### Add extra functionality

* Add an endpoint to calculate the sum of the rating for a given forecastId

## Forecast Service

### Responsibilities

* **Weather forecast:**   
The forecast service is responsible to provide the forecast, weather predictions to the end users.
The predictions can ether be as simple as providing the forecast to a single location,
or as complicated as providing a summary of the weather the next 3 days for a location the user is 
interested in.
* **Location management:**  
The forecast service is also responsible for storing and managing all the supported locations for 
which a forecast can be provided.
* **Rating:**  
The forecast service is responsible for collecting users rating about a specific forecast/prediction
as well as calculating and providing the total rating score.

  
### Location Endpoint

| METHOD   | Resource          |  Request Body  | Description                       |
|:---------|-------------------|:--------------:|-----------------------------------|
| **POST** | /locations/{name} |       No       | add a new Location                |
| **GET**  | /locations/{name} |       No       | returns a single location by name | 
| **GET**  | /locations        |       No       | returns all available locations   |


### Forecasts endpoints

| METHOD     | Resource                                           |             Request Body              | Description                                                  |
|:-----------|----------------------------------------------------|:-------------------------------------:|--------------------------------------------------------------|
| **GET**    | /forecasts/{name}?username={username}              |                  No                   | returns the current forecast for the user                    |
| **POST**   | /forecasts                                         |    [Yes](#forecasts-request-body)     | creates a new forecast                                       |
| **GET**    | /forecasts/hours/{name}?username={username}        |                  No                   | returns the forecast for the next 3 hours                    |
| **GET**    | /forecasts/days/{name}?username={username}         |                  No                   | returns the forecast for the next 3 days                     |
| **GET**    | /forecasts/days/summary/{name}?username={username} |                  No                   | returns the forecast summary for the next 3 days             |
| **GET**    | /forecasts/warn/{username}                         |                  No                   | returns the warning if one location of interest is dangerous |
| **GET**    | /forecasts/location/{forecast_id}                  |                  No                   | returns the forecast for a specific location                 |
| **DELETE** | /forecasts/{forecast_id}                           |                  No                   | deletes a forecast                                           |
| **GET**    | /forecasts/rating/{location_id}                    |                  No                   | returns the forecast rating for a specific location          |
| **POST**   | /rating                                            | [Yes](#Rating-endpoints-request-body) | adds a rating for a specific forecast                        |
| **DELETE** | /rate/{rate_id}                                    |                  No                   | deletes a rating                                             |

#### forecasts request body
`/forecasts`
```json
  {
        "localTime": "",
        "localDate": "",
        "temperature": 0,
        "humidity": 0.0,
        "wind": 0,
        "category": "",
        "serviceName": "",
        "locationName": ""
    }
```

#### Rating endpoints request body
`* /rating*`
```json
    {
        "username": "",
        "password": "",
        "forecastId": 0,
        "score": 0
    }
```

## Forecast Provider Service

| METHOD     | Resource                      |                 Request Body                  | Description                              |
|:-----------|-------------------------------|:---------------------------------------------:|------------------------------------------|
| **POST**   | /forecast-providers/{name}    |                      No                       | add a new forecast provider              |
| **PUT**    | /forecast-providers/{oldName} | [Yes](#Rename-forecast-provider-request-body) | renames a forecast provider              | 
| **GET**    | /forecast-providers           |                      No                       | returns all available forecast providers |
| **GET**    | /forecast-providers/{name}    |                      No                       | returns a specific forecast providers    |
| **DELETE** | /forecast-providers/{name}    |                      No                       | deletes a forecast provider              |


#### Rename forecast provider request body
`PUT /forecast-providers/{oldName}`
```json
    {
        "serviceName": ""
    }
```

## User Service

| METHOD     | Resource                             |                      Request Body                      | Description                              |
|:-----------|--------------------------------------|:------------------------------------------------------:|------------------------------------------|
| **GET**    | /users/{username}                    |                           No                           | gets user info by username               |
| **POST**   | /users/login                         |            [Yes](#Users-login-request-body)            | login for users                          | 
| **POST**   | /users                               |        [Yes](#Register-a-new-user-request-body)        | registers a new user                     |
| **POST**   | /users/location                      |     [Yes](#Add-a-location-to-a-user-request-body)      | adds a new location to the user          |
| **POST**   | /users/forecast-providers            | [Yes](#Add-a-forecast-provider-to-a-user-request-body) | adds a new forecast provider to the user |
| **GET**    | /users/location/{username}           |                           No                           | gets user info by username               |
| **GET**    | /users/forecast-providers/{username} |                           No                           | gets user info by username               |


#### Users login request body
```json
    {
        "username": "",
        "password": ""
    }
```

#### Register a new user request body
```json
{
  "username": "",
  "password": "",
  "location": ""
}
```

### Add a location to a user request body
```json
{
  "username": "",
  "password": "",
  "location": ""
}
```

#### Add a forecast provider to a user request body
```json
{
  "username": "",
  "password": "",
  "forecastProvider": ""
}
```