package org.baranovskiy.attractions.sevice;

import org.baranovskiy.attractions.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    Optional<City> getById(Long id);

    void save(City city);

    void delete(Long id);

    List<City> getAll();
}
