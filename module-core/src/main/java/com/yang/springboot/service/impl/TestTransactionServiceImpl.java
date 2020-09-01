package com.yang.springboot.service.impl;

import com.yang.springboot.jpa.TeacherDO;
import com.yang.springboot.repo.TeacherRepo;
import com.yang.springboot.service.TestTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Yang Hao
 * @date 2020/9/1
 */
@Service
@Slf4j
public class TestTransactionServiceImpl implements TestTransactionService {


    @Resource
    private TeacherRepo teacherRepo;


    @Override
    @Transactional
    public void serviceA() {
        TeacherDO teacherDO = new TeacherDO("zhangsan", "jiangsu");
        teacherRepo.save(teacherDO);

//        try {
//            serviceB();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void serviceB() {
        TeacherDO teacherDO = new TeacherDO("lisi", "shanghai");
        teacherRepo.save(teacherDO);
        throw new RuntimeException("serviceB 发生了异常--------------");
    }


}
