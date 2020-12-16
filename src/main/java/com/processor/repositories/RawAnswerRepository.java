package com.processor.repositories;

import com.processor.domain.RawAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RawAnswerRepository extends MongoRepository<RawAnswer, String> {

    RawAnswer findOneByEntryId(String entryId);

}
