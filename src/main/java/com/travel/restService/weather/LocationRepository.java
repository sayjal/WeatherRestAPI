package com.travel.restService.weather;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository  
public interface LocationRepository extends JpaRepository<Location, String>{
	
	@Query("select a from Location a where trim(lower(a.city)) = trim(lower(:city)) and trim(lower(a.state)) = trim(lower(:state))")
//	 @Query("select a from Location a where a.city = :city and a.state = :state")
	 	Location existsByCordinates(
	      @Param("city") String city, @Param("state") String state);
	
}
