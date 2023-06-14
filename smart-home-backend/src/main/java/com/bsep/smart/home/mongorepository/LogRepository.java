package com.bsep.smart.home.mongorepository;

import com.bsep.smart.home.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public interface LogRepository extends MongoRepository<Log, UUID> {
    Page<Log> getLogsByPropertyId(String propertyId, Pageable pageable);


    @Query("{$or:[{'createdAt':{$regex:?0}}, {'message':{$regex:?0}}, {'propertyId':  {$regex: ?0}}, {'deviceId': {$regex:  ?0}}], 'propertyId': ?1, 'processed':  true}")
    Page<Log> searchLogsByRegexAndPropertyId(Pattern regex, String id, Pageable pageable);

    @Query("{$or:[{'createdAt':{$regex:?0}}, {'message':{$regex:?0}}, {'propertyId':  {$regex: ?0}}, {'deviceId': {$regex:  ?0}}], 'propertyId': ?1, 'deviceId':  ?2, 'processed': false}")
    List<Log> getLogsByRegexAndPropertyIdAndDeviceId(Pattern regex, String propertyId, String deviceId);

    List<Log> getLogsByPropertyId(String id);
}
