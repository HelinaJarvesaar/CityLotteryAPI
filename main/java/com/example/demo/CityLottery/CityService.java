package com.example.demo.CityLottery;

import java.util.List;
import java.util.Random;

public class CityService {

    private final ICityRepository cityRepository;
    private int seed;

    public CityService(ICityRepository cityRepository, int seed) {
        this.cityRepository = cityRepository;
        this.seed = seed;
    }

    public CityService(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    /*
     * 0. Prepare a list of cities
     * 1. Count total amount of citizens -> 100
     * 2. Choose random number -> 56
     * 3. Loop going through all the cities
     * 4. Choose the city with correct lottery ticket
     * */

    public City getRandomCity() throws Exception {
        var cities = cityRepository.getCities();

        // Check if city list is empty
        if (cities.isEmpty()) {
            throw new Exception("No cities available.");
        }
        int totalCitizenCount = getCitizenCount(cities);

        // Ensure totalCitizenCount is greater than 0
        if (totalCitizenCount <= 0) {
            throw new Exception("Total population of all cities must be greater than 0.");
        }
        int randomValue = getRandomNumber(totalCitizenCount);
        return getCity(cities, randomValue);
    }

    // 1. Count total amount of citizens -> 100
    public int getCitizenCount(List<City> cities) {
        int totalCitizenCount = 0;
        for (City city : cities) {
            totalCitizenCount += city.getPopulation();
        }
        return totalCitizenCount;
    }

    // 2. Choose random number -> 56
    // If we have seed, then random = new Random(seed) else random = new Random()
    public int getRandomNumber(int totalCitizenCount) {
        Random random = (seed != 0)
                ? new Random(seed)
                : new Random();

        return random.nextInt(totalCitizenCount);// totalCitizenCount is guaranteed to be > 0
    }

    // 3. Loop going through all the cities
    // 4. Choose the city with correct lottery ticket
    // population -> 25
    // randomValue -> 56
    // We subtract 56 - 25 = 31
    // BECAUSE ITS NOT BELOW OR EQUAL TO 0, GO TO NEXT
    // 31 - 75 -> because it's below 0, we choose this city
    // GET CITY

    public City getCity(List<City> cities, int randomValue) throws Exception {
        if(cities.isEmpty()){
            throw new Exception("City list is empty.");  //YOU WOULD THROW AN EXCEPTION HERE
        }
        for (City city : cities) {
            randomValue -= city.getPopulation();
            if (randomValue <= 0) {
                return city;
            }
        }
        throw new Exception("Something went wrong.");
    }
}