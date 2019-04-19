package com.yang.springboot.repo;

import com.yang.springboot.domain.jpa.Waybill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author yanghao
 * @date 2019-04-18 16:44
 */
public interface WaybillRepo extends JpaRepository<Waybill, String>, JpaSpecificationExecutor<Waybill> {

    /**
     * 删除运单
     *
     * @param billCode
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByBillCode(String billCode);

    /**
     * 查询运单
     * @param billCode
     * @return
     */
    Waybill findOneByBillCode(String billCode);
}
