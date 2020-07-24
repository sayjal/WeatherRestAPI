package com.travel.restService.weather;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;


@Entity
@Table(name="location")
public class Location {
	
	@Id 
	@GeneratedValue
	private int id;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Weather> weather;
	
	@Digits(integer=3,fraction=4,message="Lattiude format ###.####") 
	private float lat;
	
	@Digits(integer=3,fraction=4,message="Longitude format ###.####") 
	private float lon;
	
	private String city;
	private String state;
	
	
	public Location() {
		super();
	}
	
	public Location( float lat, float lon,  String city, String state) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.city = city;
		this.state = state;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public float getLat() {
		return this.lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	
	@Override
	public String toString() {
		return String.format("Location [id=%s,  lat=%s, lon=%s, city=%s, state=%s]", id, 
				lat, lon, city, state);
	}

	
}
