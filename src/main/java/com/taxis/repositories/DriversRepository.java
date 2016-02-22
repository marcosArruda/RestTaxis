package com.taxis.repositories;

import com.taxis.entities.Driver;
import org.springframework.data.geo.Box;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marcosarruda on 2/13/16.
 */
@Repository
public interface DriversRepository extends MongoRepository<Driver, String> {
    long countByNameAndCarPlate(String name, String carPlate);
    List<Driver> findByCurrentPositionPointWithin(Box box);
    Driver findByName(String name);
    Driver findByCarPlate(String carPlate);
}