## Migrating to microservices

Link to [GitHub docs](https://github.com/billk97/weather-api/blob/master/src/site/markdown/microservices-migration.md)

The application will be split into 3 microservices. UserService, ForecastProviderService and ForecastService.
The following schema shows the 3 microservices.

The communication between the microservices is described in the following schema.

![image](/src/site/markdown/uml/reqs/microservices-comunication.jpg)


* User service communicates with the Service provider to get the available forecast provider services.
Inorder to add them to the user as the preferred forecast provider
* User service communicates with the Forecast service to get the available locations. 
Inorder to add them to the user as the location of interest
* Forecast service communicated with the user service in order to get the users preferred locations
and forecast providers to create a custom prediction.
* The forecast service communicates with the Forecast provider service to get info for all or a specific
forecast provider in order to register/save a forecast/prediction

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

| METHOD   | Resource                |       Request Body       | Description                              |
|:---------|-------------------------|:------------------------:|------------------------------------------|
| **POST** | /locations/             | [Yes](#Add-new-location) | add a new Location                       |
| **GET**  | /locations/{locationId} |            No            | returns a single location by locationId  |
| **GET**  | /locations              |            No            | returns all available locations          |


### Forecasts endpoints

| METHOD     | Resource                                          |             Request Body              | Description                                                  |
|:-----------|---------------------------------------------------|:-------------------------------------:|--------------------------------------------------------------|
| **GET**    | /forecasts/{name}?userId={userId}                 |                  No                   | returns the current forecast for the user                    |
| **POST**   | /forecasts                                        |    [Yes](#forecasts-request-body)     | creates a new forecast                                       |
| **GET**    | /forecasts//hours/{numberOfHours}?userId={userId} |                  No                   | returns the forecast for the next 3 hours                    |
| **GET**    | /forecasts/days/{numberOfDays}?userId={userId}    |                  No                   | returns the forecast for the next 3 days                     |
| **GET**    | /forecasts/days/summary/?userId={userId}          |                  No                   | returns the forecast summary for the next 3 days             |
| **GET**    | /forecasts/warn/?userId={userId}                  |                  No                   | returns the warning if one location of interest is dangerous |
| **GET**    | /forecasts/location/{forecast-id}                 |                  No                   | returns the forecast for a specific location                 |
| **DELETE** | /forecasts/{forecast-id}                          |                  No                   | deletes a forecast                                           |

### Rating endpoints

| METHOD     | Resource                 |             Request Body              | Description                                                    |
|:-----------|--------------------------|:-------------------------------------:|----------------------------------------------------------------|
| **POST**   | /rating                  | [Yes](#Rating-endpoints-request-body) | adds a rating for a specific forecast                          |
| **DELETE** | /rate/{rate-id}          |                  No                   | deletes a rating                                               |
| **GET**    | /forecasts/{location-id} |                  No                   | returns the forecast rating for a specific location            |

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
`/rating`
```json
    {
        "userId": "",
        "forecastId": 0,
        "score": 0
    }
```

## Forecast Provider Service

### Responsibilities

* **Managing forecast providers**  
The forecast provider service is responsible for managing (creating, deleting, renaming) the  
forecast providers. A forecast provider is an external entity that provided forecast predictions  
to the system.


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

### Responsibilities

* **Managing users**  
  The user service is responsible for managing the systems users (creating, authorizing, editing  
user preferences). With the temp user we describe the end user of the system that users the api   
in order to get informed about the weather.  


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
`/users/login`
```json
    {
        "username": "",
        "password": ""
    }
```

#### Register a new user request body
`/users`
```json
{
  "username": "",
  "password": "",
  "location": ""
}
```

### Add a location to a user request body
`/users/location`
```json
{
  "username": "",
  "password": "",
  "location": ""
}
```

### Add new location
`/locations`
```json
{
  "locationName": "string",
  "latitude": 0,
  "longitude": 0
}
```


#### Add a forecast provider to a user request body
`/users/forecast-providers`
```json
{
  "username": "",
  "password": "",
  "forecastProvider": ""
}
```