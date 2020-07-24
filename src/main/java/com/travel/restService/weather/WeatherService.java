package com.travel.restService.weather;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class WeatherService {

	@Autowired
	private WeatherRepository weatherRepository;

	@Autowired
	private LocationRepository locationRepository;

	// Retrieve all weather data for given Date
	public List<Weather> findWeatherByDate(LocalDate date) {
		return weatherRepository.findAllByDate(date);
	}

	// Retrieve all weather data in ascending order by weatherId
	public List<Weather> getAllWeatherData() {
		return weatherRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	// Add New Weather
	public boolean saveWeatherData(Weather wb) {
		// If Weather Id already exists. Do not add data.
		if (weatherRepository.existsById(wb.getId())) {
			return false;
		}
		// Get Location details.
		Location locOptional = locationRepository.existsByCordinates(wb.getLocation().getCity(),
				wb.getLocation().getState());
		// If location information does not exists, add data for Location and Weather.
		if (null == locOptional) {
			locationRepository.save(wb.getLocation());
			weatherRepository.save(wb);
		} else {
			// Location data exists. Add only weather data.
			wb.setLocation(locOptional);
			weatherRepository.save(wb);
		}
		return true;
	}

	// Delete all weather data
	public void deleteAllWeatherData() {
		weatherRepository.deleteAll();
		locationRepository.deleteAll();
	}
}
