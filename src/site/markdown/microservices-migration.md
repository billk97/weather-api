## Migrating to microsservices

The application will be split into 3 microservices. UserService, ForcastProviderService and ForecastService.
The following schema shows the 3 microservices.

The communication between the microservices is described in the following schema.

![image](/src/site/markdown/uml/reqs/microservices-comunication.jpg)


### Add extra functionality

* Add en enpoint to calculate the sum of the rating for a given forecastId

## Forecast Service

### Location Endpoint

* addNewLocation **POST locations/{name}**

* getLocationByName **GET locations/{name}**

* getAllLocations **GET locations**

### Forecasts endpoints

* getCurrentForecastForeUser **GET forecasts/{name}?username=aaa**

* addForecast **POST forecast/**  
**Request Body:** 

```json
    {
        "localTime": "",
        "localDate": "",
        "temperature": 0,
        "humidity": 0.0,
        "wind": 0,
        "category": "",
        "serviceName": "",
        "locationName": "",
    }
```

* getNextThreeHoursForecast **GET forecasts/hours/{name}?usrname=aaa**

* getNextThreeDays **GET forecasts/days/{name}?usrname=aaa**

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

## User-Forecast service communication
