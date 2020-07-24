package com.travel.restService.weather;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService weatherService;

	@MockBean
	private WeatherRepository weatherRepository;
	
	@MockBean
	private LocationRepository locationRepository;

	
	private static Location loc = new Location(33.30f, 43.30f, "Buffalo Grove", "Illinois");
	private static LocalDate date = LocalDate.parse("2020-07-20");
	
	private static ArrayList<Float> temp = new ArrayList<Float>();

	static {
		temp.add(new Float(37.3));
		temp.add(new Float(36.8));
		temp.add(new Float(36.4));
		temp.add(new Float(36.0));
		temp.add(new Float(35.6));
		temp.add(new Float(35.3));
		temp.add(new Float(35.0));
	}

	private static List<Weather> weatherList = Arrays.asList(new Weather(2002, date, loc, temp), new Weather(2001, date, loc, temp));
	private Weather existingWeather = new Weather(2001, date, loc, temp);

	
	
	@Test
	public void getAllWeather_basic() throws Exception {
		// call GET "/weather" application/json
		when(weatherService.getAllWeatherData()).thenReturn(weatherList);

		RequestBuilder request = MockMvcRequestBuilders.get("/weather").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("[{id:2001}, {id:2002}]", false)).andReturn();

	}

	@Test
	public void getWeatherByDate_basic() throws Exception {
		// call GET "/weather?date={date}" application/json
		when(weatherService.findWeatherByDate(date)).thenReturn(weatherList);

		RequestBuilder request = MockMvcRequestBuilders.get("/weather/{date}", "2020-07-20")
				.accept(MediaType.APPLICATION_JSON).queryParam("date", "2020-07-20");

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("[{id:2001,date:2020-07-20}, {id:2002,date:2020-07-20}]", false)).andReturn();

	}

	@Test
	public void deleteAllWeather_basic() throws Exception {
		// call GET "/weather?date={date}" application/json

		RequestBuilder request = MockMvcRequestBuilders.delete("/erase").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

	}
	
	@Test
	public void saveWeather_failure() throws Exception {
		//call POST "/weather"  application/json
		
		when(weatherService.saveWeatherData(existingWeather)).thenReturn(false);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/weather")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":2001,\"date\":\"2020-07-23\",\"location\":{\"lat\":44.9866,\"lon\":-93.2581,\"city\":\"Palo Alto\",\"state\":\"California\"},\"temperature\":[37.0,37.3,37.9,38.3,38.6,38.9,39.3,39.6,39.9,40.3,40.5,41.0,42.3,42.9,43.0,44.3,42.3,41.3,41.3,40.3,38.3,37.3,37.4,37.9]}")
				.contentType(MediaType.APPLICATION_JSON);
		
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isBadRequest())				
				.andReturn();

	}
	
	
}
