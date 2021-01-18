package com.yang.springboot.service.impl;

import com.yang.springboot.jpa.TeacherDO;
import com.yang.springboot.repo.TeacherRepo;
import com.yang.springboot.service.TestTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * @author Yang Hao
 * @date 2020/9/1
 */
@Service
@Slf4j
public class TestTransactionServiceImpl implements TestTransactionService {

    @Resource
    private TeacherRepo teacherRepo;

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void serviceA() {
        TeacherDO teacherDO = new TeacherDO("zhangsan", "jiangsu");
        teacherRepo.save(teacherDO);


        serviceB();

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void serviceB() {
        threadPoolExecutor.execute(() -> {
            for (int i = 0; i < 100; i++) {
                TeacherDO teacherDO = new TeacherDO("lisi" + i, "shanghai" + i);
                teacherRepo.save(teacherDO);
                throw new RuntimeException("serviceB 发生了异常--------------");
            }

        });


    }


}
