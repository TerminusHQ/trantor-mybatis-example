package io.terminus.trantor.demo.myBatis.model;

import io.terminus.trantor.api.annotation.*;
import io.terminus.trantor.api.annotation.typemeta.RelationMeta;

/**
 * Created by hedy on 2021/9/6.
 */
@TModel(name = "员工模型", mainField = StaffModel.name_field, fieldGroups = {
        @FieldGroup(type = FieldGroupType.DEFAULT_SHOW, fieldName = {StaffModel.id_field, StaffModel.name_field})
})
public class StaffModel extends BaseModel<Long> {

    @TModelField(name = "员工名称")
    private String name;

    @TModelField(name = "领用设备")
    @RelationMeta(name = "StaffEquipmentId", modelClass = EquipmentModel.class)
    private EquipmentModel equipment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EquipmentModel getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentModel equipment) {
        this.equipment = equipment;
    }
}
