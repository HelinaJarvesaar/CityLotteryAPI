package com.example.demo.CityLottery;

import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CityRepository implements ICityRepository {
    //mock db
    @Override
    public List<City> getCities(){ // this is declaration
        // You can return mock data for testing
        List<City> cities = new ArrayList<>();
        var goog = new City("Goog", 75);
        var wocity = new City("Wocity", 25);
        var oskarscity = new City("Oskars city", 25);
        cities.add(goog);
        cities.add(wocity);
        cities.add(oskarscity);
        return cities;

       /* try (var factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(City.class)
                .buildSessionFactory()) {
            // Everything above should be in a different class
            // And factory itself should be using dependency injection
            try (var session = factory.openSession()) {
                session.beginTransaction();
                var query = session.createQuery("from City", City.class);
                return query.getResultList();
            }
        }*/
    }
}
