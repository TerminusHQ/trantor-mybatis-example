package io.terminus.trantor.demo.myBatis.dao;

import io.terminus.trantor.demo.myBatis.model.StaffModel;
import io.terminus.trantor.sdk.autumn.dao.TrantorDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by hedy on 2021/9/10.
 */
@Repository
public class StaffDAO extends TrantorDAO<StaffModel, Long> {
}
