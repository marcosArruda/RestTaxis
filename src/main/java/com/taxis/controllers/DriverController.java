package com.taxis.controllers;

import com.taxis.business.EndpointFactory;
import com.taxis.entities.Driver;
import com.taxis.entities.DriverPosition;
import com.taxis.entities.Greeting;
import com.taxis.entities.describer.Describe;
import com.taxis.entities.dtos.DriverDTO;
import com.taxis.entities.dtos.DriverPositionDTO;
import com.taxis.repositories.DriversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DriverController {

	@Autowired
	private DriversRepository driversRepository;

	@RequestMapping("/describe")
	public Describe describe() {
		Describe describe = new Describe();
		describe.setEndpoints(EndpointFactory.buildEndpoints());
		return describe;
	}

	//FINISHED
	@RequestMapping(value="/drivers", method=RequestMethod.POST)
	public void postDriver(@RequestBody DriverDTO driver) {
		if(driver.getName() != null && driver.getCarPlate() != null
				&& driversRepository.countByNameAndCarPlate(driver.getName(), driver.getCarPlate()) < 1){
			Driver d = new Driver(driver.getName(), driver.getCarPlate());
			d.setDriverAvailable(true);
			driversRepository.insert(d);
		}
	}

	//FINISHED
	@RequestMapping(value="/drivers/{driverId}/status", method=RequestMethod.POST)
	public void postDriverStatus(@RequestBody DriverPositionDTO driverPositionDTO, @PathVariable(value = "driverId") String driverId) {
		if(driversRepository.exists(driverId)){
			Driver d = driversRepository.findOne(driverId);
			d.setDriverAvailable(driverPositionDTO.getDriverAvailable());
			d.setCurrentPosition(driverPositionDTO.toEntity(driverId));
			driversRepository.save(d);
		}
	}

	//FINISHED
	@RequestMapping(value="/drivers/{driverId}/status", method=RequestMethod.GET)
	public DriverPositionDTO getDriverStatus(@PathVariable(value = "driverId") String driverId) {
		if(driversRepository.exists(driverId)){
			Driver d = driversRepository.findOne(driverId);

			DriverPositionDTO dto = new DriverPositionDTO();
			dto.setDriverAvailable(d.getDriverAvailable());
			dto.setDriverId(d.getDriverId());

			if(d.getCurrentPosition() != null){
				dto.setLatitude(d.getCurrentPosition().getLatitude());
				dto.setLongitude(d.getCurrentPosition().getLongitude());
				return dto;
			}else
				return DriverPositionDTO.buildDriverCurrentPositionNotFound(dto);
		}else
			return DriverPositionDTO.buildDriverNotFound(driverId);
	}

	/*
	2 - Lista os taxistas ativos dentro de uma retângulo geográfico.
		a. Parâmetros:
			i.  sw: Ponto extremo sul, extremo oeste do retângulo, no
				 formato "latitude,longitude". Ex: -23.612474,-46.702746
			ii. ne: Ponto extremo norte, extremo leste do retângulo,
				 no formato "latitude,longitude". Ex: -23.589548,-46.673392
		b. Resposta: Um array em formato json, de um objeto com atributos:
			i. latitude
			ii. longitude
			iii. driverId (id único de taxista no sistema)
			iv. driverAvailable: true/false. Representa se o taxista está disponível ou não
					para corridas. Neste endpoint retorna sempre true.
		c. Exemplo:
		GET /drivers/inArea?sw=-23.612474,-46.702746&ne=-23.589548,-46.673392
		return:
		[
			{"latitude":-23.60810717,"longitude":-46.67500346,"driverId":5997,"driverAvailable":true},
			{"latitude":-23.59065045044675,"longitude":-46.68837101634931,"driverId":63446,"driverAvailable":true},
			{"latitude":-23.60925506,"longitude":-46.69390415,"driverId":1982,"driverAvailable":true},
			{"latitude":-23.599871666666665,"longitude":-46.680903333333326,"driverId":9106,"driverAvailable":true},
			{"latitude":-23.59492613,"longitude":-46.69024011,"driverId":16434,"driverAvailable":true}
		]
	 */
	@RequestMapping(value="/drivers/inArea", method=RequestMethod.GET)
	public List<DriverPositionDTO> getDriverStatus(@RequestParam("sw") double[] sw, @RequestParam("ne") double[] ne) {
		//TODO: Write the find, and return the list;

		DriverPositionDTO dtoSW = new DriverPositionDTO();
		dtoSW.setDriverId("SW");

		dtoSW.setLatitude(Double.valueOf(sw[0]));
		dtoSW.setLongitude(Double.valueOf(sw[1]));

		DriverPositionDTO dtoNE = new DriverPositionDTO();
		dtoNE.setDriverId("NE");

		dtoNE.setLatitude(Double.valueOf(ne[0]));
		dtoNE.setLongitude(Double.valueOf(ne[1]));

		List<DriverPositionDTO> list = new ArrayList<DriverPositionDTO>();
		list.add(dtoSW);
		list.add(dtoNE);

		return list;
	}
}
