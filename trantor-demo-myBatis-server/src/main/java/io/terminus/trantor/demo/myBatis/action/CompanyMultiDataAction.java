package io.terminus.trantor.demo.myBatis.action;

import io.terminus.trantor.demo.myBatis.dao.CompanyDAO;
import io.terminus.trantor.demo.myBatis.model.CompanyModel;
import io.terminus.trantor.demo.myBatis.model.StaffModel;
import io.terminus.trantor.sdk.autumn.Selectable;
import io.terminus.trantor.sdk.datasource.MultiDataAction;
import io.terminus.trantor.sdk.datasource.MultiDataParams;
import io.terminus.trantor.sdk.datasource.MultiDataResult;
import io.terminus.trantor.sdk.query.QueryValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hedy on 2021/9/6.
 */
@Component
public class CompanyMultiDataAction implements MultiDataAction<CompanyModel> {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public MultiDataResult<CompanyModel> load(QueryValues queryValues, MultiDataParams multiDataParams) {
        List<CompanyModel> data = companyDAO.find(query -> {
            query.select(select -> {
                select.getAll();
                select.getObject(CompanyModel.staffs_field, staffsField -> staffsField.select(staffSelect -> {
                    staffSelect.getAll();
                    staffSelect.getObject(StaffModel.equipment_field, Selectable::selectAll);
                }));
            });
            if (multiDataParams.getPaging() != null) {
                query.limit(multiDataParams.getPaging().getNo() - 1, multiDataParams.getPaging().getSize());
            }
        });
        return new MultiDataResult<>(data, (long) data.size());
    }
}