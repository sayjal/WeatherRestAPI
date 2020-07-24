# WeatherRestAPI

It is a coding exercise project to build set of REST services. 
Technology stack: Spring Boot, JPA, H2, JUnit. 

Exposed services are:

1.	Get : /weather
Info: Get All weather data, sorted ascending by id
Output: All weather data sorted in ascending order of Weather Id. HTTP Response 200.

2.	Get : /weather?date={date}  or /weather/{date}
Info: Get weather data recorded on input date. 
Output: Weather data of given date. 

3.	Post: /weather
Info: Save weather information. 
Input: JSON as below. 
Output: If weather Id already exists, HTTP Response code should be 400. 
	Else weather data should be added and HTTP Response code should be 201. 

4.	Delete: /erase 
Info: Delete all weather Info. 
Output: HTTP Response code 200. 


Requirements for this project is as below: 

you are part of a team building a travel company platform. One requirement is for a REST API service to provide weather information using some framework.  

The definitions and a detailed requirements list follow.

Each weather data is a JSON entry with the following keys:

id: This is the unique weather data ID.
date: This is the weather data record date given in the format yyyy-MM-dd.
location: The place for which the weather data was recorded. The location itself is a JSON object consisting of the following fields:
lat: The latitude (up to four decimal places) of the location.
lon: The longitude (up to four decimal places) of the location.
city: This is the city name.
state: This is the state name.
temperature: This is an array of 24 float values (up to one decimal place), describing the hourly temperature (in F) for the given location.
 

Sample JSON weather data object:

{
   "id":1,
   "date":"1985-01-01",
   "location”: {
      "lat":36.1189,
      "lon”: -86.6892,
      "city":"Palo Alto",
      "state":"California"
   },
   "temperature”: [ 37.3,36.8,36.4,36.0,35.6,35.3,
      35.0,34.9,35.8,38.0,40.2,42.3,43.8,
      44.9,45.5,45.7,44.9,43.0,
      41.7,40.8,39.9,39.2,38.6,38.1
   ]
}


The REST service should implement the following functionalities:
 

Adding new weather data: The service should be able to add a new weather data by the POST request at /weather. The weather JSON is sent in the request body. If weather data with the same ID already exists then the HTTP response code should be 400; otherwise, the response code should be 201.

Returning all the weather data: The service should be able to return the JSON array of all the weather data by the GET request at /weather. The HTTP response code should be 200. The JSON array should be sorted in ascending order of weather data ID.

Returning the weather data filtered by date: The service should be able to return the JSON array of all the weather data recorded on the given date by the GET request at /weather?date={date}.

Erasing all the weather data: The service should be able to erase all the weather data by the DELETE request at /erase. The HTTP response code should be 200.
