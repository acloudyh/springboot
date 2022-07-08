package com.yang.springboot.controller;

import com.yang.springboot.jpa.DataDemoDO;
import com.yang.springboot.jpa.PersonInfoDO;
import com.yang.springboot.repo.CourseRepo;
import com.yang.springboot.repo.DataDemoRepo;
import com.yang.springboot.repo.PersonInfoRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Yang Hao
 * @date 2021-03-12 20:08
 */
@RestController
@Slf4j
@RequestMapping(value = "/web/neo/course")
@Api(tags = "人员信息测试")
public class TestCourseController {

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private PersonInfoRepo personInfoRepo;
    @Autowired
    private DataDemoRepo dataDemoRepo;



    @RequestMapping(value = "/batch", method = RequestMethod.GET)
    @ApiOperation("创建人员信息")
    public void createBatchCourse() {
        long startTime = System.currentTimeMillis();
        List<PersonInfoDO> personInfoDOS = new ArrayList<>();
        int num = 100000;
        int max = 20;
        int min = 1;
        int maxMounth = 12;

        for (int i = 0; i < num; i++) {
            PersonInfoDO personInfoDO = new PersonInfoDO();

            int courseId = new Random().nextInt(max - min) + min;
            personInfoDO.setCourseId((long) courseId);
            int mouth = new Random().nextInt(maxMounth - min) + min;
            personInfoDO.setMonth((long) mouth);
            personInfoDO.setCourseId((long) courseId);
            if ((i & 1) != 0) {
                personInfoDO.setPassed((short) 1);
            } else {
                personInfoDO.setPassed((short) 0);
            }

            personInfoDOS.add(personInfoDO);

//            if (personInfoDOS.size() >= 3000) {
//                personInfoRepo.saveAll(personInfoDOS);
//                personInfoDOS.clear();
//            }
        }
        personInfoRepo.saveAll(personInfoDOS);

        long endTime = System.currentTimeMillis();
        log.info("插入{}条数据耗时:{} ms", num, endTime - startTime);
    }


    @RequestMapping(value = "/batch2", method = RequestMethod.GET)
    @ApiOperation("创建人员信息2")
    @Transactional(rollbackFor = Exception.class)
    public void createBatchCourse2() {
        long startTime = System.currentTimeMillis();
        List<DataDemoDO> dataDemoDOS = new ArrayList<>();
        int num = 500000;
        int max = 20;
        int min = 1;
        int maxMounth = 12;

        for (int i = 0; i < num; i++) {
            DataDemoDO dataDemoDO = new DataDemoDO();

            int courseId = new Random().nextInt(max - min) + min;
            dataDemoDO.setCourseId((long) courseId);
            int mouth = new Random().nextInt(maxMounth - min) + min;
            dataDemoDO.setMonth((long) mouth);
            dataDemoDO.setCourseId((long) courseId);
            if ((i & 1) != 0) {
                dataDemoDO.setPassed((short) 1);
            } else {
                dataDemoDO.setPassed((short) 0);
            }

            dataDemoDOS.add(dataDemoDO);

//            if (dataDemoDOS.size() >= 3000) {
//                personInfoRepo.saveAll(dataDemoDOS);
//                dataDemoDOS.clear();
//            }
        }
        dataDemoRepo.saveAll(dataDemoDOS);

        long endTime = System.currentTimeMillis();
        log.info("插入{}条数据耗时:{} ms", num, endTime - startTime);
    }


    public static void main(String[] args) {

        int max = 20;
        int min = 1;
        for (int i = 0; i < 10; i++) {
            System.out.println(new Random().nextInt(max - min) + min);
        }
    }
}




