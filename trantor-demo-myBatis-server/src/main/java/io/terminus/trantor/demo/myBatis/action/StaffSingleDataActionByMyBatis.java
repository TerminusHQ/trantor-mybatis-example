package io.terminus.trantor.demo.myBatis.action;

import io.terminus.trantor.demo.myBatis.mapper.StaffMapper;
import io.terminus.trantor.demo.myBatis.model.StaffModel;
import io.terminus.trantor.sdk.datasource.SingleDataAction;
import io.terminus.trantor.sdk.query.QueryValues;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by hedy on 2021/9/6.
 */
@Component
public class StaffSingleDataActionByMyBatis implements SingleDataAction<StaffModel> {

    @Resource
    private StaffMapper mapper;

    @Override
    public StaffModel load(QueryValues queryValues) {
        StaffModel staffModel = mapper.findById(Long.valueOf(queryValues.getOneValue("id").toString()));
        return staffModel;
    }
}