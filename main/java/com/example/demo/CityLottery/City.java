package com.example.demo.CityLottery;

import jakarta.persistence.Entity; // persistence could be called as repository
import jakarta.persistence.Id;

@Entity
public class City {

    @Id
    private String name;
    private int population;

    public City(String name, int population){
        this.name = name;
        this.population = population;
    }

    public City(){
    }

    public String getName(){
        return name;
    }

    public int getPopulation(){
        return population;
    }

}

