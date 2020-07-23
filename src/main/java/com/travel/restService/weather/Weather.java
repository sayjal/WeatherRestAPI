package com.travel.restService.weather;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity 
@Table(name="weather")
public class Weather {

	@Id
	@NotNull
	private int id;
	
	private LocalDate date;

	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name = "locationId", referencedColumnName="id")
	@Valid
	private Location location;

	
	@ElementCollection
	@CollectionTable(name ="temperature")
	@Size(min=24,max=24,message="Must input temperatures for 24 hours")
	private List<@Digits(integer=3,fraction=1,message="All Temperatures must have 1 decimal points") Float> temperature; 
	
	public Weather() {
		
	}

	public Weather(int id, LocalDate date, Location loc, ArrayList<Float> temp) {
		super();
		this.id = id;
		this.date = date;
		this.location = loc;
		this.temperature = temp;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Float> getTemperature() {
		return temperature;
	}

	public void setTemperature(List<Float> temperature) {
		this.temperature = temperature;
	}	
	
	@Override
	public String toString() {
		return String.format("Weather [id=%s, date=%s, temperature=%s]", id, date, temperature);
	}

	
}
