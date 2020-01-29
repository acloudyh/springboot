package com.yang.springboot.controller;

import com.yang.springboot.jpa.StudentEventDO;
import com.yang.springboot.service.StudentEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanghao
 * @date 2019-05-29 10:54
 */
@RestController
@Slf4j
@RequestMapping(value = "/web/neo/student")
@Api(tags = "测试mongo")
public class StudentEventController {

    @Resource
    private StudentEventService studentEventService;

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("批量创建学生，测试mongo ")
    public void createBatchStudent() {
        List<StudentEventDO> studentEventDOS = new ArrayList<>();
        int num = 10;

        for (int i = 0; i < num; i++) {
            StudentEventDO studentEventDO = new StudentEventDO();
            studentEventDO.setUserName("student" + i);
            studentEventDO.setPassWord("password" + i);
            studentEventDOS.add(studentEventDO);
        }

        studentEventService.createBatchStudentEvent(studentEventDOS);

    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("查询所有信息（分页）")
    public Page<StudentEventDO> listStudentInfo(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return studentEventService.listStudentEvent(pageable);
    }
}
