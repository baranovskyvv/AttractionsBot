package org.baranovskiy.attractions.controller;

import org.baranovskiy.attractions.model.City;
import org.baranovskiy.attractions.sevice.CityService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/cities/")
public class CityRestController {
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<City> getCity(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return this.cityService.getById(id).map(x -> new ResponseEntity<>(x, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<City> saveCity(@RequestBody @Valid City city) {
        HttpHeaders headers = new HttpHeaders();
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.cityService.save(city);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<City> updateCity(@RequestBody @Valid City city) {
        HttpHeaders headers = new HttpHeaders();

        if (city == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.cityService.save(city);
        return new ResponseEntity<>(city, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<City> deleteCity(@PathVariable("id") Long id) {
        City city = this.cityService.getById(id).get();

        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.cityService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = this.cityService.getAll();

        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
