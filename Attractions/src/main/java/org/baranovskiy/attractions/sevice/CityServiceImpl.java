package org.baranovskiy.attractions.sevice;

import lombok.extern.slf4j.Slf4j;
import org.baranovskiy.attractions.model.City;
import org.baranovskiy.attractions.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository countryRepository;

    @Transactional
    @Override
    public Optional<City> getById(Long id) {
        log.info("IN CountryServiceImpl getById{}", id);
        return countryRepository.findById(id);
    }

    @Override
    public void save(City city) {
        log.info("IN CountryServiceImpl save{}", city);
                countryRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        log.info("IN CountryServiceImpl delete{}", id);
        countryRepository.deleteById(id);
    }

    @Override
    public List<City> getAll() {
        log.info("IN CountryServiceImpl getAll");
        return countryRepository.findAll();
    }
}
