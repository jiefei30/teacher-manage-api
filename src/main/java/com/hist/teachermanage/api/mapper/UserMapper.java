package com.hist.teachermanage.api.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hist.teachermanage.api.BO.UserDepartmentBO;
import com.hist.teachermanage.api.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * <p>
 * 教职工用户表 Mapper 接口
 * </p>
 *
 * @author Wangmingcan
 * @since 2020-07-22
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * @author Wangmingcan
     * @date 2020-07-23 16:04
     * @param page
     * @param queryWrapper
     * @return java.util.List<com.hist.teachermanage.api.BO.UserDepartmentBO>
     * @description
     */
    @Select("SELECT u.*,d.* FROM user u LEFT JOIN department d ON u.dep_id = d.id ${ew.customSqlSegment}")
    @Result(property = "department",column = "dep_id",
            one = @One(select = "com.hist.teachermanage.api.mapper.DepartmentMapper.selectById",
                    fetchType = FetchType.EAGER))
    @Result(column = "dep_id",property = "depId")
    IPage<UserDepartmentBO> selectVo(Page page, @Param(Constants.WRAPPER) QueryWrapper<UserDepartmentBO> queryWrapper);
}
