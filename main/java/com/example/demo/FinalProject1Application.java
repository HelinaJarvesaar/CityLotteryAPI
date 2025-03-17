package com.example.demo;
import com.example.demo.CityLottery.CityRepository;
import com.example.demo.CityLottery.CityService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class FinalProject1Application {

	public static void main(String[] args) throws Exception {
		var cityService = new CityService(new CityRepository());
		var randomCity = cityService.getRandomCity();
		System.out.println(randomCity.getName());

	}
}