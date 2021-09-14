package io.terminus.trantor.demo.myBatis.action;

import io.terminus.trantor.demo.myBatis.mapper.StaffMapper;
import io.terminus.trantor.demo.myBatis.model.StaffModel;
import io.terminus.trantor.sdk.datasource.MultiDataAction;
import io.terminus.trantor.sdk.datasource.MultiDataParams;
import io.terminus.trantor.sdk.datasource.MultiDataResult;
import io.terminus.trantor.sdk.query.QueryValues;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hedy on 2021/9/6.
 */
@Component
public class StaffMultiDataActionByMyBatis implements MultiDataAction<StaffModel> {

    @Resource
    private StaffMapper mapper;

    @Override
    public MultiDataResult<StaffModel> load(QueryValues queryValues, MultiDataParams multiDataParams) {
        long no = 0, size = 10;
        if (multiDataParams.getPaging() != null) {
            no = multiDataParams.getPaging().getNo() - 1;
            size = multiDataParams.getPaging().getSize();
        }
        List<StaffModel> data = mapper.findAll(no, size);
        return new MultiDataResult<>(data, (long) data.size());
    }
}