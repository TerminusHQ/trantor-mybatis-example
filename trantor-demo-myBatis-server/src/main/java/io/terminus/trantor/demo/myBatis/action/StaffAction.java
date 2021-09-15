package io.terminus.trantor.demo.myBatis.action;

import io.terminus.trantor.api.TContext;
import io.terminus.trantor.api.annotation.TAction;
import io.terminus.trantor.demo.myBatis.dao.StaffDAO;
import io.terminus.trantor.demo.myBatis.mapper.StaffMapper;
import io.terminus.trantor.demo.myBatis.model.StaffModel;
import io.terminus.trantor.sdk.autumn.client.DSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by hedy on 2021/9/6.
 */
@Component
public class StaffAction {

    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private DSClient dsClient;

    @Resource
    private StaffMapper staffMapper;

    @TAction(modelClass = StaffModel.class)
    public void create(StaffModel staffModel) {
        staffDAO.create(staffModel);
    }

    @TAction(modelClass = StaffModel.class)
    public void delete(StaffModel staffModel) {
        staffDAO.delete(staffModel.getId());
    }

    @TAction(modelClass = StaffModel.class)
    public void update(StaffModel staffModel) {
        staffDAO.save(staffModel);
    }

    @TAction(modelClass = StaffModel.class)
    public void createByMyBatis(StaffModel staffModel) {
        staffModel.setId(dsClient.nextId(StaffModel.class));
        staffModel.setCreatedAt(new Date());
        staffModel.setUpdatedAt(new Date());
        staffModel.setIsDeleted(false);
        staffModel.setCreatedBy(TContext.getCurrentUser());
        staffModel.setUpdatedBy(TContext.getCurrentUser());
        staffMapper.insert(staffModel);
    }

    @TAction(modelClass = StaffModel.class)
    public void deleteByMyBatis(StaffModel staffModel) {
        staffMapper.delete(staffModel);
    }

    @TAction(modelClass = StaffModel.class)
    public void updateByMyBatis(StaffModel staffModel) {
        staffModel.setUpdatedAt(new Date());
        staffMapper.update(staffModel);
    }


}
