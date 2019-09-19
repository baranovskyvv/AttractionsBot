package org.baranovskiy.attractions.repo;

import org.apache.el.stream.Optional;
import org.baranovskiy.attractions.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select description from City where city=:i")
    List findByCity(@Param("i") String city);

    @Query("select city from City")
    List findCities();
}
