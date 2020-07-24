package com.travel.restService.weather;

import java.net.URI;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class WeatherController {

	@Autowired
	private WeatherService service;

	// Get Weather information for given date.
	// URI: weather?date={date}
	@GetMapping(path = "/weather", params = "date")
	public List<Weather> findWeatherByDate(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws ParseException {
		return service.findWeatherByDate(date);
	}

	// Get weather information for given date
	// URI: /weather/{date}
	@GetMapping(path = "/weather/{date}")
	public List<Weather> getWeatherByDate(
			@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws ParseException {
		return service.findWeatherByDate(date);
	}

	// Get all Weather information in ascending order of weather id.
	@GetMapping(path = "/weather")
	public List<Weather> getAllWeather() {
		return service.getAllWeatherData();
	}

	// Save new weather information.
	@PostMapping(path = "/weather")
	public ResponseEntity<Object> saveWeather(@Valid @RequestBody Weather wb) {
		boolean created = service.saveWeatherData(wb);
		if (!created) {
			throw new UserExistsException("id =" + wb.getId() + " already exists.");
		}
		URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{date}").buildAndExpand(wb.getDate()).toUri();
		return ResponseEntity.created(loc).build();
	}

	// Delete all weather information.
	@DeleteMapping(path = "/erase")
	public void deleteAllWeather() {
		service.deleteAllWeatherData();
	}
}
