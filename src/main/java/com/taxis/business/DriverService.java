package com.taxis.business;

import com.taxis.entities.Driver;
import com.taxis.entities.dtos.DriverDTO;
import com.taxis.entities.dtos.DriverPositionDTO;
import com.taxis.repositories.DriversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosarruda on 2/21/16.
 */
@Service
public class DriverService {

    @Autowired
    private DriversRepository driversRepository;

    public DriverPositionDTO insertDriver(DriverDTO driver){
        DriverPositionDTO dto = new DriverPositionDTO();
        dto.setDriverAvailable(true);

        if(driver.getName() != null && driver.getCarPlate() != null
                && driversRepository.countByNameAndCarPlate(driver.getName(), driver.getCarPlate()) < 1){
            Driver d = new Driver(driver.getName(), driver.getCarPlate());
            d.setDriverAvailable(true);
            driversRepository.insert(d);



            if(d.getDriverId() != null){
                dto.setDriverId(d.getDriverId());
            }else{
                Driver dr = driversRepository.findByCarPlate(driver.getCarPlate());
                dto.setDriverId(dr.getDriverId());
            }

        }
        return dto;
    }

    public void deleteDriver(String driverId){
        driversRepository.delete(driverId);
    }

    public void updateDriverStatus(DriverPositionDTO driverPositionDTO, String driverId){
        if(driversRepository.exists(driverId)){
            Driver d = driversRepository.findOne(driverId);
            d.setDriverAvailable(driverPositionDTO.getDriverAvailable());
            d.setCurrentPosition(driverPositionDTO.toEntity(driverId));
            driversRepository.save(d);
        }
    }

    public DriverPositionDTO getDriverStatus(String driverId){
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

    public String getDriverIdByName(String name){
        Driver d = driversRepository.findByName(name);
        return d != null ? d.getDriverId() : "NOT_FOUND";


    }

    public String getDriverIdByCarPlate(String carPlate){
        Driver d = driversRepository.findByCarPlate(carPlate);
        return d != null ? d.getDriverId() : "NOT_FOUND";
    }

    public List<DriverPositionDTO> findDriverInBox(double[] sw, double[] ne){
        Box b = new Box(new Point(sw[0],sw[1]), new Point(ne[0], ne[1]));
        List<Driver> inPositionDrivers = driversRepository.findByCurrentPositionPointWithin(b);
        List<DriverPositionDTO> list = new ArrayList<DriverPositionDTO>();
        for(Driver d : inPositionDrivers){
            DriverPositionDTO dDTO = new DriverPositionDTO(d.getCurrentPosition().getLatitude(), d.getCurrentPosition().getLongitude(), d.getDriverAvailable());
            dDTO.setDriverId(d.getDriverId());
            list.add(dDTO);
        }
        return list;
    }
}
