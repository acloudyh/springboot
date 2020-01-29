package com.yang.springboot.service;

import com.yang.springboot.jpa.StudentEventDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yanghao
 * @date 2019-05-29 10:52
 */
public interface StudentEventService {
    void createBatchStudentEvent(List<StudentEventDO> studentEventDOS);

    Page<StudentEventDO> listStudentEvent(Pageable pageable);
}
