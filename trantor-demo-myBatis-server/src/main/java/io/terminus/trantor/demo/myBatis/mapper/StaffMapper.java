package io.terminus.trantor.demo.myBatis.mapper;

import io.terminus.trantor.demo.myBatis.model.StaffModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hedy on 2021/9/10.
 */
public interface StaffMapper {

    void insert(StaffModel staff);

    void delete(StaffModel staff);

    void update(StaffModel staff);

    Long getId();

    List<StaffModel> findAll(@Param("no") Long no, @Param("size") Long size);

    StaffModel findById(@Param("staffId")Long staffId);

    List<StaffModel> findByCompanyId(@Param("companyId") Long companyId);

    void updateCompanyIdByStaffId(@Param("ids") List<Long> ids, @Param("companyId") Long companyId);

}
