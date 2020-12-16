package com.processor.repositories;

import com.processor.domain.TestConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestConfigurationRepository extends MongoRepository<TestConfiguration, String> {

    /**
     * Returns the TestConfiguration associated to formCode
     * @param formCode
     * @return
     */
    TestConfiguration findOneByFormCode(String formCode);
	
}
