package com.example.app.service;


import com.example.app.domain.Forecast;
import com.example.app.domain.Location;
import com.example.app.domain.Service;
import com.example.app.domain.User;
import com.example.app.enums.WeatherCategory;
import com.example.app.repository.ForecastRepository;
import com.example.app.repository.LocationRepository;
import com.example.app.repository.ServiceRepository;
import com.example.app.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Forecast service.
 */
public class ForecastService {

    private ServiceRepository serviceRepository  = new ServiceRepository();
    private LocationRepository locationRepository = new LocationRepository();
    private ForecastRepository forecastRepository = new ForecastRepository();
    private UserRepository userRepository = new UserRepository();

    /**
     * Add new forecast int.
     *
     * @param localTime       the local time
     * @param localDate       the local date
     * @param temperature     the temperature
     * @param humidity        the humidity
     * @param wind            the wind
     * @param weatherCategory the weather category
     * @param serviceName     the service name
     * @param locationName    the location name
     * @return the int
     */
    public int addNewForecast(LocalTime localTime,
                               LocalDate localDate, int temperature,
                               float humidity, int wind,
                               WeatherCategory weatherCategory,
                               String serviceName,
                               String locationName){
        Optional<Service> service = serviceRepository.findByName(serviceName);
        Optional<Location> location = locationRepository.findOneByName(locationName);

        if(!service.isPresent() || !location.isPresent()){
            throw new IllegalArgumentException("no service of location with that name");
        }
        Forecast forecast = new Forecast(localTime, localDate, temperature,
                humidity,wind,weatherCategory,service.get(), location.get());
        forecastRepository.save(forecast);
        System.out.println(forecast.getId());
        return forecast.getId();
    }

    /**
     * Get current forecast forecast.
     *
     * @param username the username
     * @param location the location
     * @param mockTime the mock time
     * @return the forecast
     */
    public Forecast getCurrentForecast(String username, String location, boolean mockTime){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Optional<Location> locationOptional = locationRepository.findOneByName(location);
        List<Forecast> forecasts = new ArrayList<>();
        if (!optionalUser.isPresent()){
            throw new IllegalArgumentException("user with username: "+ username + " doesn't exist");
        }
        if(!locationOptional.isPresent()){
            throw new IllegalArgumentException("no such location: "+ location);
        }
        forecasts = forecastRepository.findCurrentByLocation(locationOptional.get(), mockTime);
        if(forecasts.isEmpty()){
            throw new IllegalArgumentException("no forecasts fore current time");
        }
        return getAverageForecast(forecasts);
    }

    private Forecast getAverageForecast(List<Forecast> forecasts) {
        Forecast finalForecast = new Forecast();
        int totalTemp = 0 ;
        int totalWind = 0 ;
        float totalHumidity = 0 ;
        for (Forecast forecast : forecasts){
            totalTemp += forecast.getTemperature();
            totalWind += forecast.getWind();
            totalHumidity += forecast.getHumidity();
        }
        finalForecast.setTemperature(totalTemp/ forecasts.size());
        finalForecast.setWind(totalWind/ forecasts.size());
        finalForecast.setHumidity(totalHumidity/ forecasts.size());
        finalForecast.setWeatherCategory(forecasts.get(0).getWeatherCategory());
        finalForecast.setLocation(forecasts.get(0).getLocation());
        finalForecast.setLocalDate(forecasts.get(0).getLocalDate());
        finalForecast.setLocalTime(forecasts.get(0).getLocalTime());
        return finalForecast;
    }

    /**
     * Gets next three hours forecast.
     *
     * @param username the username
     * @param location the location
     * @param mockTime the mock time
     * @return the next three hours forecast
     */
    public List<Forecast> getNextThreeHoursForecast(String username, String location, Boolean mockTime) {
        LocalTime CURRENT_TIME = LocalTime.now();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Optional<Location> locationOptional = locationRepository.findOneByName(location);
        List<Forecast> forecastsNow = new ArrayList<>();
        List<Forecast> forecastsNextOneHour = new ArrayList<>();
        List<Forecast> forecastsNextTwoHour = new ArrayList<>();

        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("user with username: " + username + " doesn't exist");
        }
        if (!locationOptional.isPresent()) {
            throw new IllegalArgumentException("no such location: " + location);
        }
        forecastsNow = forecastRepository.findByLocationTime(locationOptional.get(), CURRENT_TIME, mockTime);
        forecastsNextOneHour = forecastRepository.findByLocationTime(locationOptional.get(), CURRENT_TIME.plusHours(1), mockTime);
        forecastsNextTwoHour = forecastRepository.findByLocationTime(locationOptional.get(), CURRENT_TIME.plusHours(2), mockTime);
        List<Forecast> finalForecast = new ArrayList<>();
        finalForecast.add(getAverageForecast(forecastsNow));
        finalForecast.add(getAverageForecast(forecastsNextOneHour));
        finalForecast.add(getAverageForecast(forecastsNextTwoHour));

        return finalForecast;
    }

    /**
     * Get next three days list.
     *
     * @param username the username
     * @param location the location
     * @param mockTime the mock time
     * @return the list
     */
    public List<Forecast> getNextThreeDays(String username, String location, Boolean mockTime){
        LocalDate CURRENT_DATE = LocalDate.now();
        if(mockTime){
            CURRENT_DATE = LocalDate.of(2020,11,23);
        }
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Optional<Location> locationOptional = locationRepository.findOneByName(location);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("user with username: " + username + " doesn't exist");
        }
        if (!locationOptional.isPresent()) {
            throw new IllegalArgumentException("no such location: " + location);
        }
        List<Forecast> finalForecastList = new ArrayList<>();
        List<Forecast> tempForecast = new ArrayList<>();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(1),
                LocalTime.of(6,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(1),
                LocalTime.of(3,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(1),
                LocalTime.of(20,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(2),
                LocalTime.of(6,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(2),
                LocalTime.of(3,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(2),
                LocalTime.of(20,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(3),
                LocalTime.of(6,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(3),
                LocalTime.of(3,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(3),
                LocalTime.of(20,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        return finalForecastList;
    }
    public List<Forecast> getSingleNextThreeDays(String username, String location, Boolean mockTime){
        LocalDate CURRENT_DATE = LocalDate.now();
        if(mockTime){
            CURRENT_DATE = LocalDate.of(2020,11,23);
        }
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Optional<Location> locationOptional = locationRepository.findOneByName(location);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("user with username: " + username + " doesn't exist");
        }
        if (!locationOptional.isPresent()) {
            throw new IllegalArgumentException("no such location: " + location);
        }
        List<Forecast> finalForecastList = new ArrayList<>();
        List<Forecast> tempForecast = new ArrayList<>();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(1),
                LocalTime.of(3,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(2),
                LocalTime.of(3,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        tempForecast = forecastRepository.findByLocationDateAndTime(locationOptional.get(), CURRENT_DATE.plusDays(3),
                LocalTime.of(3,0));
        finalForecastList.add(getAverageForecast(tempForecast));
        tempForecast.clear();
        return finalForecastList;
    }

    public String warnUsers(String username, boolean mock){
        LocalDate localDate  = LocalDate.now();
        if(mock){
            localDate = LocalDate.of(2020,11,23);
        }
        Optional<User> userOptional =  userRepository.findByUsername(username);
        if(!userOptional.isPresent()){
            throw new IllegalArgumentException("There is no user with name: " + username);
        }
        if(userOptional.get().getLocation() == null){
            throw new IllegalArgumentException("user has no location saved: ");
        }
        List<Forecast> forecasts = forecastRepository.findByLocation(userOptional.get().getLocation() );
        for (Forecast forecast : forecasts){
            if(!forecast.getLocalDate().equals(localDate)){
                continue;
            }
            if(forecast.isBadWeather()){
                return "There is a bad weather warning for today in location: " + userOptional.get().getLocation().getCityName() ;
            }
        }
        return "There is no bad weather warning for today in location: " + userOptional.get().getLocation().getCityName() ;
    }
}
