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


| METHOD   | Resource                                     |          Request Body          | Description                               |
|:---------|----------------------------------------------|:------------------------------:|-------------------------------------------|
| **GET**  | /forecasts/{name}?username={username}        |               No               | returns the current forecast for the user |
| **POST** | /forecasts                                   | [Yes](#forecasts-request-body) | creates a new forecast                    |
| **GET**  | /forecasts/hours/{name}?username={username}  |               No               | returns the forecast for the next 3 hours |

#### forecasts request body
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


* getNextThreeDays **GET forecasts/days/{name}?username=aaa**

* getNextThreeDaysSummary **GET forecasts/days/summary/{name}?username=aaa**

* getWarningsForToday **GET forecasts/warn/username**

* getForecastByLocation **GET forecasts/location/{forecast_id}**

* deleteForecast **DELETE forecasts/{forecast_id}**

* getForecastRating **GET forecasts/rating/{location_id}**


### Rating endpoints

* addRatingToForecast **POST rate**

```json
    {
        "username": "",
        "password": "",
        "forecastId": 0,
        "score": 0
    }
```

* deleteRating **DELETE rate/{id}**

## Forevast Provider Service

* addNewForcastProvider **POST forecast_providers/{name}**

* changeForcastProviderName **PUT forecast_providers/{oldName}**

```json
    {
        "serviceName": ""
    }
```

* getAllServices **GET forecast_providers**

* getServiceByName **GET forecast_providers/{name}**

* deleteProvider **DELETE forecast_providers/{name}**


## User Service

* getUser **GET users/{username}**

* login **GET users/login**

```json
    {
        "username": "",
        "password": ""
    }
```

* register **POST users**

```json
    "username": "",
    "password": "",
    "location": "",
```

* addLocation **POST users/location**

```json
    "username": "",
    "password": "",
    "location": "",
```

* addForecastProviders **POST user/forecast_providers**

```json
    "username": "",
    "password": "",
    "forecastProvider": "",
```

* getLocation **GET users/location/{username}**

* getForecastProviders **GET forecast_providers/{username}**
