package com.taxis.controllers;

import com.taxis.business.DriverService;
import com.taxis.business.EndpointFactory;
import com.taxis.entities.Driver;
import com.taxis.entities.describer.Describe;
import com.taxis.entities.dtos.DriverDTO;
import com.taxis.entities.dtos.DriverIdDTO;
import com.taxis.entities.dtos.DriverPositionDTO;
import com.taxis.repositories.DriversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosarruda on 2/13/16.
 */
@RestController
public class DriverController {

	@Autowired
	private DriverService driverService;

	@RequestMapping("/describe")
	public Describe describe() {
		Describe describe = new Describe();
		describe.setEndpoints(EndpointFactory.buildEndpoints());
		return describe;
	}

	//FINISHED
	@RequestMapping(value="/drivers", method=RequestMethod.POST)
	public void postDriver(@RequestBody DriverDTO driver) {
		driverService.insertDriver(driver);
	}

	//FINISHED
	@RequestMapping(value="/drivers/ret", method=RequestMethod.POST)
	public DriverIdDTO postDriverRet(@RequestBody DriverDTO driver) {
		DriverIdDTO dto = new DriverIdDTO();
		DriverPositionDTO d = driverService.insertDriver(driver);
		if(d != null){
			dto.setDriverId(d.getDriverId());
		}
		return dto;
	}

	//FINISHED
	@RequestMapping(value="/drivers/{driverId}", method=RequestMethod.DELETE)
	public void deleteDriver(@PathVariable(value = "driverId") String driverId) {
		driverService.deleteDriver(driverId);
	}

	//FINISHED
	@RequestMapping(value="/drivers/{driverId}/status", method=RequestMethod.POST)
	public void postDriverStatus(@RequestBody DriverPositionDTO driverPositionDTO, @PathVariable(value = "driverId") String driverId) {
		driverService.updateDriverStatus(driverPositionDTO, driverId);
	}

	@RequestMapping(value="/drivers/name/{strname}", method=RequestMethod.GET)
	public DriverIdDTO getDriverByName(@PathVariable(value = "strname") String strname) {
		DriverIdDTO dto = new DriverIdDTO();
		dto.setDriverId(driverService.getDriverIdByName(strname));
		return dto;
	}

	@RequestMapping(value="/drivers/carplate/{strcarplate}", method=RequestMethod.GET)
	public DriverIdDTO getDriverByCarPlate(@PathVariable(value = "strcarplate") String strcarplate) {
		DriverIdDTO dto = new DriverIdDTO();
		dto.setDriverId(driverService.getDriverIdByCarPlate(strcarplate));
		return dto;
	}

	//FINISHED
	@RequestMapping(value="/drivers/{driverId}/status", method=RequestMethod.GET)
	public DriverPositionDTO getDriverStatus(@PathVariable(value = "driverId") String driverId) {
		return driverService.getDriverStatus(driverId);
	}

	/*
		MOCKS:
		sw[0] = -23.579556
		sw[1] = -46.625912

		ne[0] = -23.571611
		ne[1] = -46.614003

		notIncluded:
		lat: -23.572201 long: -46.610720 driverId: 56c665ac30045e7956c3ab78
		lat: -23.573027 long: -46.631620 driverId: 56c665b530045e7956c3ab79
		lat: -23.576429 long: -46.631577 driverId: 56c665bd30045e7956c3ab7a
		lat: -23.574974 long: -46.630311 driverId: 56c665c530045e7956c3ab7b

		included:
		lat: -23.575072 long: -46.623015 driverId: 56c6657a30045e7956c3ab73
		lat: -23.576528 long: -46.623037 driverId: 56c6658530045e7956c3ab74
		lat: -23.577137 long: -46.619239 driverId: 56c6658d30045e7956c3ab75
		lat: -23.577452 long: -46.622221 driverId: 56c6659730045e7956c3ab76
		lat: -23.574010 long: -46.622436 driverId: 56c665a130045e7956c3ab77

		{ "_id" : ObjectId("56c6657a30045e7956c3ab73"), "name" : "Pedro", "carPlate" : "RPC-9999", "driverAvailable" : true, "currentPosition" : { "date" : ISODate("2016-02-19T00:52:29.798Z"), "driverId" : "56c6657a30045e7956c3ab73", "latitude" : -23.575072, "longitude" : -46.623015, "point" : [ -23.575072, -46.623015 ] } }
		{ "_id" : ObjectId("56c6658530045e7956c3ab74"), "name" : "Marcos", "carPlate" : "RPC-9998", "driverAvailable" : true, "currentPosition" : { "date" : ISODate("2016-02-19T00:52:57.140Z"), "driverId" : "56c6658530045e7956c3ab74", "latitude" : -23.576528, "longitude" : -46.623037, "point" : [ -23.576528, -46.623037 ] } }
		{ "_id" : ObjectId("56c6658d30045e7956c3ab75"), "name" : "Joao", "carPlate" : "RPC-9997", "driverAvailable" : true, "currentPosition" : { "date" : ISODate("2016-02-19T00:54:39.976Z"), "driverId" : "56c6658d30045e7956c3ab75", "latitude" : -23.577137, "longitude" : -46.619239, "point" : [ -23.577137, -46.619239 ] } }
		{ "_id" : ObjectId("56c6659730045e7956c3ab76"), "name" : "Jonas", "carPlate" : "RPC-9996", "driverAvailable" : true, "currentPosition" : { "date" : ISODate("2016-02-19T00:55:06.173Z"), "driverId" : "56c6659730045e7956c3ab76", "latitude" : -23.577452, "longitude" : -46.622221, "point" : [ -23.577452, -46.622221 ] } }
		{ "_id" : ObjectId("56c665a130045e7956c3ab77"), "name" : "Leticia", "carPlate" : "RPC-9995", "driverAvailable" : true, "currentPosition" : { "date" : ISODate("2016-02-19T00:55:38.424Z"), "driverId" : "56c665a130045e7956c3ab77", "latitude" : -23.57401, "longitude" : -46.622436, "point" : [ -23.57401, -46.622436 ] } }
		{ "_id" : ObjectId("56c665ac30045e7956c3ab78"), "name" : "Gui", "carPlate" : "RPC-9994", "driverAvailable" : true, "currentPosition" : { "date" : ISODate("2016-02-19T00:50:16.157Z"), "driverId" : "56c665ac30045e7956c3ab78", "latitude" : -23.572201, "longitude" : -46.61072, "point" : [ -23.572201, -46.61072 ] } }
		{ "_id" : ObjectId("56c665b530045e7956c3ab79"), "name" : "Trouxa", "carPlate" : "RPC-9993", "driverAvailable" : true, "currentPosition" : { "date" : ISODate("2016-02-19T00:50:48.218Z"), "driverId" : "56c665b530045e7956c3ab79", "latitude" : -23.573027, "longitude" : -46.63162, "point" : [ -23.573027, -46.63162 ] } }
		{ "_id" : ObjectId("56c665bd30045e7956c3ab7a"), "name" : "Maluco", "carPlate" : "RPC-9992", "driverAvailable" : true, "currentPosition" : { "date" : ISODate("2016-02-19T00:51:17.559Z"), "driverId" : "56c665bd30045e7956c3ab7a", "latitude" : -23.576429, "longitude" : -46.631577, "point" : [ -23.576429, -46.631577 ] } }
		{ "_id" : ObjectId("56c665c530045e7956c3ab7b"), "name" : "Bestalhao", "carPlate" : "RPC-9991", "driverAvailable" : true, "currentPosition" : { "date" : ISODate("2016-02-19T00:51:49.691Z"), "driverId" : "56c665c530045e7956c3ab7b", "latitude" : -23.574974, "longitude" : -46.630311, "point" : [ -23.574974, -46.630311 ] } }


	 */
	//FINISHED
	@RequestMapping(value="/drivers/inArea", method=RequestMethod.GET)
	public List<DriverPositionDTO> findInArea(@RequestParam("sw") double[] sw, @RequestParam("ne") double[] ne) {
		return driverService.findDriverInBox(sw, ne);
	}
}
