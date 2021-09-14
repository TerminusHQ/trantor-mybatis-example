package io.terminus.trantor.demo.myBatis.model;

import io.terminus.trantor.api.annotation.*;
import io.terminus.trantor.api.annotation.typemeta.RelationMeta;

/**
 * Created by hedy on 2021/9/6.
 */
@TModel(name = "办公设备模型", mainField = EquipmentModel.name_field, fieldGroups = {
        @FieldGroup(type = FieldGroupType.DEFAULT_SHOW, fieldName = {EquipmentModel.id_field, EquipmentModel.name_field})
})
public class EquipmentModel extends BaseModel<Long> {

    @TModelField(name = "设备名称")
    private String name;

    @TModelField(name = "领用员工")
    @RelationMeta(name = "StaffEquipmentId", modelClass = StaffModel.class)
    private StaffModel staff;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StaffModel getStaff() {
        return staff;
    }

    public void setStaff(StaffModel staff) {
        this.staff = staff;
    }
}
