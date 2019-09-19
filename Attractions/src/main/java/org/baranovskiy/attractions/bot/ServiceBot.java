package org.baranovskiy.attractions.bot;

import lombok.extern.slf4j.Slf4j;
import org.baranovskiy.attractions.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ServiceBot {
    @Autowired
    CityRepository cityRepository;

    String findCity(String city) {
        List list = cityRepository.findByCity(city);
        if (list.isEmpty()) {
            return "Такого города нет в базе данных";
        }
        return list.toString();
    }

    String findAllCities() {
        List list = cityRepository.findCities();
        if (list.isEmpty()) {
            return "Нет городов";
        }
        return "Список доступных городов: " + list.toString();
    }
}
