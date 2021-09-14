package io.terminus.trantor.demo.myBatis.action;

import io.terminus.trantor.api.TContext;
import io.terminus.trantor.api.annotation.TAction;
import io.terminus.trantor.demo.myBatis.dao.CompanyDAO;
import io.terminus.trantor.demo.myBatis.mapper.CompanyMapper;
import io.terminus.trantor.demo.myBatis.mapper.StaffMapper;
import io.terminus.trantor.demo.myBatis.model.CompanyModel;
import io.terminus.trantor.demo.myBatis.model.StaffModel;
import io.terminus.trantor.sdk.autumn.client.DSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hedy on 2021/9/6.
 */
@Component
public class CompanyAction {

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private DSClient dsClient;

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private StaffMapper staffMapper;

    @TAction(modelClass = CompanyModel.class)
    public void create(CompanyModel companyModel) {
        companyDAO.createConnectRelation(companyModel);
    }

    @TAction(modelClass = CompanyModel.class)
    public void delete(CompanyModel companyModel) {
        companyDAO.delete(companyModel.getId());
    }

    @TAction(modelClass = CompanyModel.class)
    public void update(CompanyModel companyModel) {
        companyDAO.save(companyModel);
    }

    @TAction(modelClass = CompanyModel.class)
    @Transactional
    public void createByMyBatis(CompanyModel companyModel) {
        // 插入company数据
        // long id = companyMapper.getId();
        // companyModel.setId(id + 1);
        companyModel.setId(dsClient.nextId(CompanyModel.class));
        companyModel.setCreatedAt(new Date());
        companyModel.setUpdatedAt(new Date());
        companyModel.setIsDeleted(false);
        companyModel.setCreatedBy(TContext.getCurrentUser());
        companyModel.setUpdatedBy(TContext.getCurrentUser());
        companyMapper.insert(companyModel);
        // 更新staff中的关联关系
        if (companyModel.getStaffs() != null) {
            List<Long> staffIds = companyModel.getStaffs().stream().map(StaffModel::getId).collect(Collectors.toList());
            if (staffIds.size() > 0) {
                staffMapper.updateCompanyIdByStaffId(staffIds, companyModel.getId());
            }
        }
    }

    @TAction(modelClass = CompanyModel.class)
    @Transactional
    public void deleteByMyBatis(CompanyModel companyModel) {
        List<Long> staffs = staffMapper.findByCompanyId(companyModel.getId()).stream().map(StaffModel::getId).collect(Collectors.toList());
        if (staffs.size() > 0) {
            staffMapper.updateCompanyIdByStaffId(staffs, null);
        }
        companyMapper.delete(companyModel);
    }

    @TAction(modelClass = CompanyModel.class)
    @Transactional
    public void updateByMyBatis(CompanyModel companyModel) {
        List<Long> oldStaffs = staffMapper.findByCompanyId(companyModel.getId()).stream().map(StaffModel::getId).collect(Collectors.toList());
        List<Long> newStaffs = companyModel.getStaffs().stream().map(StaffModel::getId).collect(Collectors.toList());
        // 找出被删除的id
        oldStaffs.removeAll(newStaffs);
        if (oldStaffs.size() > 0) {
            staffMapper.updateCompanyIdByStaffId(oldStaffs, null);
        }
        // 更新新增的id
        if (newStaffs.size() > 0) {
            staffMapper.updateCompanyIdByStaffId(newStaffs, companyModel.getId());
        }
        companyModel.setUpdatedAt(new Date());
        companyMapper.update(companyModel);
    }

}
