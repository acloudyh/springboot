package com.yang.springboot.repo;

import com.yang.springboot.jpa.DataDemoDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author yanghao
 * @date 2019-04-18 16:44
 */
public interface DataDemoRepo extends JpaRepository<DataDemoDO, Long>, JpaSpecificationExecutor<DataDemoDO> {

}
