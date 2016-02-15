package com.taxis.controllers;

import com.taxis.entities.Driver;
import com.taxis.entities.DriverPosition;
import com.taxis.entities.Greeting;
import com.taxis.repositories.DriversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DriverController {

	@Autowired
	private DriversRepository driversRepository;

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		return new Greeting(counter.incrementAndGet(),
				String.format(template, name));
	}

	@RequestMapping("/getdriver")
	public Driver testDriver() {

		Driver d = new Driver("Marcos", "EZF-3915");
		d.setDriverId(1234L);
		d.setLastPosition(new DriverPosition(BigDecimal.ONE, BigDecimal.ONE, true));
		d.setDriverAvailable(true);
		return d;

	}
}
