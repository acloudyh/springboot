package com.yang.springboot.service.impl;

import com.yang.springboot.domain.jpa.StudentEvent;
import com.yang.springboot.repo.mongo.StudentEventMongoRepo;
import com.yang.springboot.service.StudentEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanghao
 * @date 2019-05-29 10:53
 */
@Service
@Slf4j
public class StudentEventServiceImpl implements StudentEventService {

    @Resource
    private StudentEventMongoRepo studentEventMongoRepo;

    @Override
    public void createBatchStudentEvent(List<StudentEvent> studentEvents) {
        studentEventMongoRepo.save(studentEvents);
    }

    @Override
    public Page<StudentEvent> listStudentEvent(Pageable pageable) {
        return studentEventMongoRepo.findAll(pageable);
    }
}
