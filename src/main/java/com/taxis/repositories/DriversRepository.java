package com.taxis.repositories;

import com.taxis.entities.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriversRepository extends MongoRepository<Driver, Long> {

}