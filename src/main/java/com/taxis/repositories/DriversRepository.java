package com.taxis.repositories;

import com.taxis.entities.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DriversRepository extends MongoRepository<Driver, String> {
    long countByNameAndCarPlate(String name, String carPlate);
}