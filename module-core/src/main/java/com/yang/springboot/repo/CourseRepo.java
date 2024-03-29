package com.yang.springboot.repo;

import com.yang.springboot.jpa.CourseDO;
import com.yang.springboot.jpa.TeacherDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Yang Hao
 * @date 2020/9/1
 */
public interface CourseRepo extends JpaRepository<CourseDO, Long>, JpaSpecificationExecutor<CourseDO> {
}
