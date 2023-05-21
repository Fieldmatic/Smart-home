package com.bsep.smart.home.mongorepository;

import com.bsep.smart.home.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.UUID;

public interface LogRepository extends MongoRepository<Log, UUID> {
    Page<Log> getLogsByPropertyId(String propertyId, Pageable pageable);


    @Query("{$or:[{'createdAt':{$regex:?0}}, {'message':{$regex:?0}}, {'propertyId':  {$regex: ?0}}, {'deviceId': {$regex:  ?0}}]}")
    Page<Log> searchLogsByRegex(String regex, Pageable pageable);

}
