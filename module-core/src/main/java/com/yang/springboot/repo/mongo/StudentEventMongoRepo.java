package com.yang.springboot.repo.mongo;

import com.yang.springboot.domain.jpa.StudentEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author yanghao
 * @date 2019-05-28 15:40
 */
public interface StudentEventMongoRepo extends MongoRepository<StudentEvent, Integer> {

}
