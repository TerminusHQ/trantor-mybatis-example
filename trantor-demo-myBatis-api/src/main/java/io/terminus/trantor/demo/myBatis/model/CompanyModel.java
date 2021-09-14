package io.terminus.trantor.demo.myBatis.model;

import io.terminus.trantor.api.annotation.*;
import io.terminus.trantor.api.annotation.typemeta.RelationMeta;

import java.util.List;

/**
 * Created by hedy on 2021/9/6.
 */
@TModel(name = "公司模型", mainField = CompanyModel.name_field, fieldGroups = {
        @FieldGroup(type = FieldGroupType.DEFAULT_SHOW, fieldName = {CompanyModel.id_field, CompanyModel.name_field})
})
public class CompanyModel extends BaseModel<Long> {

    @TModelField(name = "公司名称")
    private String name;

    @TModelField(name = "员工")
    @RelationMeta(name = "CompanyStaffId", modelClass = StaffModel.class)
    private List<StaffModel> staffs;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StaffModel> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<StaffModel> staffs) {
        this.staffs = staffs;
    }
}
