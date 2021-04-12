package com.example.app.resource;

import com.example.app.domain.Forecast;
import com.example.app.domain.Location;
import com.example.app.dtos.ForecastDTO;
import com.example.app.dtos.WarningMessageDTO;
import com.example.app.enums.WeatherCategory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForecastResourceTest extends  ResourceTest {


    @Override
    protected Application configure() {
        return new ResourceConfig(ForecastResource.class, DebugExceptionMapper.class);
    }

    @Test
    public void given_data_endpoint_should_succeed(){
        String message = target("forecasts").request().get(String.class);
        System.out.println(message);
        assertEquals("ok", message);
    }

    @Test
    public void given_forecast_addition_should_succeed() {
        ForecastDTO forecastDTO = new ForecastDTO(LocalTime.of(1,0).toString(),
                LocalDate.of(2021,11,23).toString(),30,100f, 8,
                WeatherCategory.Cloudy.toString(),"meteo", "athens");
        Response response = target("forecasts")
                .request().post(Entity.entity(forecastDTO, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
    }

    @Test
    public void given_a_location_and_username_forecast_should_return(){
        Forecast forecast = target("forecasts/athens")
                .queryParam("username","Bill")
                .queryParam("mock",true)
                .request().get(new GenericType<Forecast>(){});
        assertEquals("athens", forecast.getLocation().getCityName());
    }
    @Test
    public void given_a_location_and_username_forecast_for_next_there_hours_should_return(){
        List<Forecast> forecast = target("forecasts/hours/athens")
                .queryParam("username","Bill")
                .queryParam("mock",true)
                .request().get(new GenericType<List<Forecast>>(){});
        assertEquals(3, forecast.size());
    }

    @Test
    public void given_a_location_and_username_forecast_for_next_there_days_should_return(){
        List<Forecast> forecast = target("forecasts/days/athens")
                .queryParam("username","Bill")
                .queryParam("mock",true)
                .request().get(new GenericType<List<Forecast>>(){});
        assertEquals(9, forecast.size());
    }
    @Test
    public void given_a_location_and_username_forecast_for_next_there_days_summery_should_return(){
        List<Forecast> forecast = target("forecasts/days/summary/athens")
                .queryParam("username","Bill")
                .queryParam("mock",true)
                .request().get(new GenericType<List<Forecast>>(){});
        assertEquals(3, forecast.size());
    }

    @Test
    public void given_user_check_if_warning_should_succeed(){
        WarningMessageDTO warningMessageDTO = target("forecasts/warn/Bill")
                .queryParam("mock",true).request().get(new GenericType<WarningMessageDTO>(){});
        assertEquals("There is no bad weather warning for today in location: athens",warningMessageDTO.getWarningMessage());
    }


}