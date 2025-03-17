package com.example.demo;

import com.example.demo.CityLottery.City;
import com.example.demo.CityLottery.CityRepository;
import com.example.demo.CityLottery.CityService;
import com.example.demo.CityLottery.ICityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class CityServiceTests {

    // when there is Goog and Wocity
    // and Goog has 10% (10citizens)
    // and Wocity has 90% (90 citizen)
    // randomizer choose 9
    // choose Goog

    @Test
    public void GIVEN_Goog83_Wocity17_WHEN_Randomizer82_THEN_ChooseGoog() throws Exception{
        // Arrange
        // 1. CityService need cityRepository in the constructor
        // 2. Make a face cityRepository
        // Instead of using db, we are using fake data
        var cityRepository = Mockito.mock(ICityRepository.class);

        // 3. create a city service, by providing fake repository
        // 4. add a seed for the city service, that gives us repeatable result
        var cityService = new CityService(cityRepository, 123);

        // 5. create list of fake cities
        var cities = new ArrayList<City>();
        // 6. add fake cities to our fake city list
        cities.add(new City("Goog", 83));
        cities.add(new City("Wocity", 17));

        // 7. set it up, that when we want to get cities,
        // we actually get these fake cities prepared in point 6

        // WHEN someone asks DB for cities
        // THEN return predetermined cities
        when(cityRepository.getCities()).thenReturn(cities);

        // Act
        var randomCity = cityService.getRandomCity();

        // Asset
        Assert.isTrue(randomCity.getName().equals("Goog"), "Goog is supposed to be chosen!");
    }
    //1. Write a MOCK test
    //  1.1. When there is only one city with 100 citizens, then choose this city
    //  1.2. When there is no seed provided, then choose whatever city
    //2. Look how to check for exceptions
    //  2.1. When there is negative citizen count in a city, then show exception
    //  2.2. When there is no cities provided, then show exception

    @Test
    public void WHEN_OneCity100_THEN_CooseCity() throws Exception{
        var cityRepository = Mockito.mock(ICityRepository.class);
        var cityService = new CityService(cityRepository);

        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("Onecity", 100));

        when(cityRepository.getCities()).thenReturn(cities);

        var randomCity = cityService.getRandomCity();

        Assert.isTrue(randomCity.getName().equals("Onecity"), "Onecity is supposed to be chosen.");

    }

    @Test
    void WHEN_NoSeedProvided_THEN_ChooseWhateverCity() throws Exception {
        ICityRepository cityRepository = Mockito.mock(ICityRepository.class);
        CityService cityService = new CityService(cityRepository);

        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("Tallinn", 150));
        cities.add(new City("Viljandi", 50));

        when(cityRepository.getCities()).thenReturn(cities);

        var randomCity = cityService.getRandomCity();

        boolean isValidCity = randomCity.getName().equals("Tallinn") || randomCity.getName().equals("Viljandi");
        Assert.isTrue(isValidCity, "A valid city should be chosen.");
    }

}
