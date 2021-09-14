package io.terminus.trantor.demo.myBatis.mapper;

import io.terminus.trantor.demo.myBatis.model.CompanyModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hedy on 2021/9/10.
 */
public interface CompanyMapper {

    int insert(CompanyModel company);

    void delete(CompanyModel company);

    void update(CompanyModel company);

    List<CompanyModel> findAll(@Param("no") Long no, @Param("size") Long size);

    Long getId();


}
