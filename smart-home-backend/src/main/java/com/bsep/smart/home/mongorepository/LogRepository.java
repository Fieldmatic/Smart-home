package com.bsep.smart.home.mongorepository;

import com.bsep.smart.home.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface LogRepository extends MongoRepository<Log, UUID> {
    List<Log> getLogsByPropertyId(String propertyId);
}
